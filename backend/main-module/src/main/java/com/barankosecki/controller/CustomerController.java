package com.barankosecki.controller;

import com.barankosecki.dto.OrderFromClientDTO;
import com.barankosecki.repository.CustomerRepository;
import com.barankosecki.repository.OrderRepository;
import com.barankosecki.repository.SubscriptionsRespository;
import com.barankosecki.service.OrderService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customers")
public class CustomerController {

    @Inject
    private CustomerRepository customerRepository;

    @Inject
    private OrderRepository orderRepository;

    @Inject
    private SubscriptionsRespository subscriptionsRespository;

    private final OrderService orderService;

    public CustomerController() {
        this.orderService = new OrderService();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCustomerById(@PathParam("id") Integer id) {
        return Response.ok(customerRepository.findById(id)).build();
    }

    @GET
    @Path("/orders")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.ok(orderRepository.findAll()).build();
    }

    @GET
    @Path("/{id}/orders")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllActiveOrdersByCustomerId(@PathParam("id") Integer id) {
        return Response.ok(orderRepository.findOrdersByCustomer(id, "ORDERED")).build();
    }

    @GET
    @Path("/{id}/history")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findHistoryByCustomerId(@PathParam("id") Integer id) {
        return Response.ok(orderRepository.findOrdersByCustomer(id, "SUPPLIED")).build();
    }

    @GET
    @Path("/{id}/subscriptions")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findSubscriptionsByCustomerId(@PathParam("id") Integer id) {
        return Response.ok(subscriptionsRespository.findAllByCustomerId(id)).build();
    }

    @POST
    @Path("/{id}/orders")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response order(@PathParam("id") Integer customerId, OrderFromClientDTO dto) {
        orderService.sendOrder(customerId, dto);
        return Response.ok().build();
    }
}
