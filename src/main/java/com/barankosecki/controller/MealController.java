package com.barankosecki.controller;

import com.barankosecki.repository.CategoryRepository;
import com.barankosecki.repository.MealRepository;
import com.barankosecki.repository.OrdersMealsRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/meals")
public class MealController {

    @Inject
    private MealRepository mealRepository;

    @Inject
    private CategoryRepository categoryRepository;

    @Inject
    private OrdersMealsRepository ordersMealsRepository;

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.ok(mealRepository.findAll()).build();
    }

    @Path("/byCategory")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllByCategory() {
        return Response.ok(mealRepository.findAllByCategory()).build();
    }


    @Path("/categories")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllCategories() {
        return Response.ok(categoryRepository.findAll()).build();
    }

    @Path("/top-meals")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findTopMeals() {
        return Response.ok(ordersMealsRepository.findTopMeals()).build();
    }
}
