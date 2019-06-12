package com.barankosecki.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
public class OrdersMealsRepository {

    private EntityManager manager;

    public OrdersMealsRepository() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA-CATERING-APP");
        this.manager = factory.createEntityManager();
    }

    public List findTopMeals() {
        try {
            return manager
                    .createQuery("SELECT m from Meal m where m.id in :mealsId")
                    .setParameter("mealsId", manager
                            .createQuery("SELECT om.identity.mealId FROM OrdersMeals om " +
                                    "group by om.identity.mealId order by count(om.identity.orderId) desc ")
                            .getResultList()
                            .subList(0, 3))
                    .getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return new LinkedList();
        }
    }
}
