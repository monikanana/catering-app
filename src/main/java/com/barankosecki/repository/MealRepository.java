package com.barankosecki.repository;

import com.barankosecki.dto.MealsByCategoryDTO;
import com.barankosecki.enitties.Category;
import com.barankosecki.enitties.Meal;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
public class MealRepository {

    private EntityManager manager;

    @Inject
    private CategoryRepository categoryRepository;

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

    public List findByCategory(Integer categoryId) {
        try {
            return manager
                    .createQuery("FROM Meal WHERE category.id=:categoryId")
                    .setParameter("categoryId", categoryId)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List findAllByCategory() {
        try {

            List<MealsByCategoryDTO> mealsByCategory = new ArrayList();

            List<Category> categories = categoryRepository.findAll();

            for(Category category : categories) {
                MealsByCategoryDTO mealsByCategoryDTO = new MealsByCategoryDTO();
                mealsByCategoryDTO.setCategoryId(category.getId());
                mealsByCategoryDTO.setCategoryName(category.getName());
                mealsByCategoryDTO.setMeals(findByCategory(category.getId()));
                mealsByCategory.add(mealsByCategoryDTO);
            }

            return mealsByCategory;

        } catch (Exception e) {
            e.printStackTrace();
            return new LinkedList();
        }
    }
}
