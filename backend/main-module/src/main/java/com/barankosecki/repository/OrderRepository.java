package com.barankosecki.repository;

import com.barankosecki.entities.Order;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
public class OrderRepository {

    private EntityManager manager;

    public OrderRepository() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA-CATERING-APP");
        this.manager = factory.createEntityManager();
    }

    public List findAll() {
        try {
            Query query = manager.createQuery("FROM Order");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new LinkedList();
        }
    }

    public List findOrdersByCustomer(Integer id, String state) {
        try {
            return manager
                    .createQuery("SELECT o FROM Order o WHERE o.customer.id=:id AND o.state=:state")
                    .setParameter("id", id)
                    .setParameter("state", state)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new LinkedList();
        }
    }

    public void save(Order order) {
        try {
            manager.getTransaction().begin();
            manager.persist(order);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
    }

    public void changeStateById(Integer orderId, String state) {
        try {
            manager.getTransaction().begin();
            Order order = findById(orderId);

            if (order.getState().equals("DELIVERED") || order.getState().equals("CANCELLED")) {
                System.out.println("To zamówienie zostało już " + order.getState());
                manager.getTransaction().rollback();
                return;
            }

            if (order.getState().equals("SUBSCRIBED")) {

                Order subscription = new Order();

                LocalDate localDate = order.getDate().toLocalDate();
                subscription.setDate(Date.valueOf(localDate.plusWeeks(1)));

                subscription.setMeals(new HashSet<>(order.getMeals()));
                subscription.setPrice(order.getPrice());
                subscription.setCustomer(order.getCustomer());
                subscription.setLocation(order.getLocation());
                subscription.setState("SUBSCRIBED");

                manager.persist(subscription);
            }

            order.setState(state);

            manager.persist(order);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
    }

    public Order findById(Integer orderId) {
        try {
            return manager.find(Order.class, orderId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List findAllByState(String state) {
        try {
            return manager
                    .createQuery("SELECT o FROM Order o WHERE o.state=:state")
                    .setParameter("state", state)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new LinkedList();
        }
    }
}
