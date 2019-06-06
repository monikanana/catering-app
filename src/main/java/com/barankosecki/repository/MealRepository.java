package com.barankosecki.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
public class MealRepository {

    private EntityManager manager;

    public MealRepository() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA-CATERING-APP");
        this.manager = factory.createEntityManager();
    }

    public List findAll() {
        try {
            return manager
                    .createQuery("FROM Meal")
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new LinkedList();
        }
    }
}
