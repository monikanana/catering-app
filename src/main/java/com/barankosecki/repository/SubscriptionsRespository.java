package com.barankosecki.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
public class SubscriptionsRespository {

    private EntityManager manager;

    public SubscriptionsRespository() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA-CATERING-APP");
        this.manager = factory.createEntityManager();
    }

    public List findAllByCustomerId(Integer id) {
        try {
            return manager
                    .createQuery("SELECT o FROM Order o WHERE o.customer.id=:id and state='SUBSCRIBED'")
                    .setParameter("id", id)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new LinkedList();
        }
    }
}
