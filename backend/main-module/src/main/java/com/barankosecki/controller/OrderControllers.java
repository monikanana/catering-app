package com.barankosecki.controller;

import com.barankosecki.repository.OrderRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/orders")
public class OrderControllers {

    @Inject
    private OrderRepository orderRepository;

    /* narazie nie widzę biznesowego zastosowania dla tej funkcji ale niech zostanie */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.ok(orderRepository.findAll()).build();
    }

    @GET
    @Path("/active")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllActiveOrders() {
        return Response.ok(orderRepository.findAllActive()).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Integer orderId) {
        orderRepository.changeStateById(orderId, "CANCELLED");
        return Response.ok().build();
    }

    @POST
    @Path("/{id}")
    public Response deliver(@PathParam("id") Integer orderId) {
        orderRepository.changeStateById(orderId, "DELIVERED");
        return Response.ok().build();
    }
}
