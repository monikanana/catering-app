package com.barankosecki.service;

import com.barankosecki.dto.OrderFromClientDTO;
import com.barankosecki.dto.SubscriptionFromClientDTO;
import com.barankosecki.entities.Meal;
import com.barankosecki.entities.Order;
import com.barankosecki.repository.CustomerRepository;
import com.barankosecki.repository.LocationRepository;
import com.barankosecki.repository.MealRepository;
import com.barankosecki.repository.OrderRepository;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class OrderService {


    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private LocationRepository locationRepository;
    private MealRepository mealRepository;


    public OrderService() {
        this.orderRepository = new OrderRepository();
        this.customerRepository = new CustomerRepository();
        this.locationRepository = new LocationRepository();
        this.mealRepository = new MealRepository();
    }

    public void sendOrder(Integer customerId, OrderFromClientDTO dto) {

        Order order = new Order();

        order.setCustomer(customerRepository.findById(customerId));
        order.setDate(dto.getDate());
        order.setLocation(locationRepository.findById(dto.getLocationId()));

        Set<Meal> meals = new HashSet<>();
        for (Integer i : dto.getMealsId()) {
            meals.add(mealRepository.findById(i));
        }
        order.setMeals(meals);

        order.setPrice(meals
                .stream()
                .mapToDouble(Meal::getPrice)
                .sum()
        );
        order.setState("ORDERED");

        orderRepository.save(order);
    }

    public void makeSubscription(Integer id, SubscriptionFromClientDTO dto) {

        for (Date date : dto.getDates()) {
            Order order = new Order();

            order.setDate(date);

            Set<Meal> meals = new HashSet<>();
            for (Integer i : dto.getMealsId()) {
                meals.add(mealRepository.findById(i));
            }
            order.setMeals(meals);

            order.setPrice(meals
                    .stream()
                    .mapToDouble(Meal::getPrice)
                    .sum()
            );

            order.setState("SUBSCRIBED");
            order.setLocation(locationRepository.findById(dto.getLocationId()));
            order.setCustomer(customerRepository.findById(id));

            orderRepository.save(order);
        }
    }

    public void cancelSubscription(Integer id) {

        Order order = orderRepository.findById(id);

        if (order.getState().equals("SUBSCRIBED")) {
            order.setState("CANCELLED");
            orderRepository.save(order);
        } else {
            System.out.println("To zamówienie nie jest aktywną subskrybcją.");
        }
    }
}
