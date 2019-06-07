package com.barankosecki.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class OrdersMealsRepository {

    private EntityManager manager;

    public OrdersMealsRepository() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA-CATERING-APP");
        this.manager = factory.createEntityManager();
    }


    // TODO
    /*public List findTopMeals() {
        try {
            return manager
                    .createQuery("" +
                            "SELECT om.identity.mealId, count(om.identity.mealId) as \'counter\'" +
                            "FROM OrdersMeals om GROUP BY om.identity.mealId ORDER BY count(om.identity.mealId) DESC")
                    .getResultList()
                    .subList(1, 11);
        } catch (Exception e) {
            e.printStackTrace();
            return new LinkedList();
        }
    }*/
}
