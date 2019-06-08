package com.barankosecki.repository;

import com.barankosecki.entities.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
public class CustomerRepository {

    private EntityManager manager;

    public CustomerRepository() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA-CATERING-APP");
        this.manager = factory.createEntityManager();
    }

    public List findAll() {

        try {
            Query query = manager.createQuery("FROM Customer");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new LinkedList();
        }
    }

    public Customer findById(Integer id) {
        try {
            return manager.find(Customer.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
