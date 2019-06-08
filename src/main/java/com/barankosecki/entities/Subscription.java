package com.barankosecki.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "subscriptions_meals",
            joinColumns = {@JoinColumn(name = "id_subscription")},
            inverseJoinColumns = {@JoinColumn(name = "id_meal")}
    )
    private Set<Meal> meals = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "subscriptions_days",
            joinColumns = {@JoinColumn(name = "id_subscription")},
            inverseJoinColumns = {@JoinColumn(name = "id_day")}
    )
    private Set<Day> days = new HashSet<>();
}
