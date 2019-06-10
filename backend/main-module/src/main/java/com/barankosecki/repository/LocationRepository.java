package com.barankosecki.repository;

import com.barankosecki.entities.Location;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
public class LocationRepository {

    private EntityManager manager;

    public LocationRepository() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA-CATERING-APP");
        this.manager = factory.createEntityManager();
    }

    public Location findById(Integer id) {
        try {
            return manager.find(Location.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List findAll() {
        try {
            return manager
                    .createQuery("FROM Location")
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new LinkedList();
        }
    }
}
