package com.barankosecki.controller;

import com.barankosecki.repository.OrderRepository;
import com.barankosecki.repository.SubscriptionsRepository;
import com.barankosecki.service.OrderService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/orders")
public class OrderControllers {

    @Inject
    private OrderRepository orderRepository;

    @Inject
    private SubscriptionsRepository subscriptionsRepository;

    private final OrderService orderService;

    public OrderControllers() {
        this.orderService = new OrderService();
    }

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
        return Response.ok(orderRepository.findAllByState("ORDERED")).build();
    }

    @GET
    @Path("/subscribed")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllSubscriptions() {
        return Response.ok(orderRepository.findAllByState("SUBSCRIBED")).build();
    }

    @POST
    @Path("/{id}/deliver")
    public Response deliver(@PathParam("id") Integer orderId) {
        orderRepository.changeStateById(orderId, "DELIVERED");
        return Response.ok().build();
    }

    /* if simple order - cancel, if subscription - cancel once, but subscription stays */
    @POST
    @Path("/{id}/cancel")
    public Response cancelById(@PathParam("id") Integer orderId) {
        orderRepository.changeStateById(orderId, "CANCELLED");
        return Response.ok().build();
    }

    /* cancels whole subscription */
    @POST
    @Path("/{id}/cancel-subscription")
    public Response cancelSubscriptionId(@PathParam("id") Integer subscriptionId) {
        orderService.cancelSubscription(subscriptionId);
        return Response.ok().build();
    }
}
