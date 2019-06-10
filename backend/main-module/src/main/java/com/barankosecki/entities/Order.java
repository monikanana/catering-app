package com.barankosecki.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Date date;

    private Double price;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "orders_meals",
            joinColumns = {@JoinColumn(name = "id_order")},
            inverseJoinColumns = {@JoinColumn(name = "id_meal")}
    )
    private Set<Meal> meals = new HashSet<>();

    private String state;

    @ManyToOne(cascade = CascadeType.ALL)
    private Location location;

}
