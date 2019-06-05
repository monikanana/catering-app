package com.barankosecki.controller;

import com.barankosecki.repository.CustomerRepository;
import com.barankosecki.repository.OrderRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customer")
public class CustomerController {

    @Inject
    private CustomerRepository customerRepository;

    @Inject
    private OrderRepository orderRepository;

    @GET
    @Path("/orders")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.ok(orderRepository.findAll()).build();
    }

    @GET
    @Path("/{id}/orders")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllOrdersByCustomerId(@PathParam("id") Integer id) {
        return Response.ok(orderRepository.findAllActiveOrdersByCustomer(id)).build();
    }
}
