package com.barankosecki.service;

import com.barankosecki.dto.OrderFromClientDTO;
import com.barankosecki.entities.Meal;
import com.barankosecki.entities.Order;
import com.barankosecki.repository.CustomerRepository;
import com.barankosecki.repository.LocationRepository;
import com.barankosecki.repository.MealRepository;
import com.barankosecki.repository.OrderRepository;

import java.util.HashSet;
import java.util.Set;

public class OrderService {

    private final MealRepository mealRepository;
    private final CustomerRepository customerRepository;
    private final LocationRepository locationRepository;
    private final OrderRepository orderRepository;

    public OrderService() {
        this.mealRepository = new MealRepository();
        this.customerRepository = new CustomerRepository();
        this.locationRepository = new LocationRepository();
        this.orderRepository = new OrderRepository();
    }

    public void sendOrder(Integer id, OrderFromClientDTO dto) {

        Order order = new Order();
        order.setDate(dto.getDate());

        Double price = 0.0;
        for (Integer mealId : dto.getMealsId()) {
            price += mealRepository.findById(mealId).getPrice();
        }
        order.setPrice(price);
        order.setState("ORDERED");
        order.setCustomer(customerRepository.findById(id));
        order.setLocation(locationRepository.findById(dto.getLocationId()));

        Set<Meal> mealSet = new HashSet<>();
        for (Integer mealId : dto.getMealsId()) {
            mealSet.add(mealRepository.findById(mealId));
        }
        order.setMeals(mealSet);
        orderRepository.save(order);
    }
}
