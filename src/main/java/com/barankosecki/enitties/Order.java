package com.barankosecki.enitties;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

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

    @ManyToOne
    @JoinColumn(name = "meal_id")
    private Meal meal;

    private Integer state;

}
