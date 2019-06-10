package com.barankosecki.repository;

import com.barankosecki.entities.Restaurant;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
public class RestaurantRepository {

    private EntityManager manager;

    public RestaurantRepository() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA-CATERING-APP");
        this.manager = factory.createEntityManager();
    }

    public Restaurant findById(Integer id) {
        try {
            return manager.find(Restaurant.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List findAll() {
        try {
            return manager
                    .createQuery("FROM Restaurant")
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new LinkedList();
        }
    }
}
