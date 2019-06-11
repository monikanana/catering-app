package com.barankosecki.repository;

import com.barankosecki.entities.Order;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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
}
