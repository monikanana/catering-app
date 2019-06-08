package com.barankosecki.controller;

import com.barankosecki.repository.RestaurantRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/restaurants")
public class RestaurantController {

    @Inject
    private RestaurantRepository restaurantRepository;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCustomerById(@PathParam("id") Integer id) {
        return Response.ok(restaurantRepository.findById(id)).build();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.ok(restaurantRepository.findAll()).build();
    }

}
