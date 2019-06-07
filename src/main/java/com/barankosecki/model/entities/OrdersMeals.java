package com.barankosecki.model.entities;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "orders_meals")
@Data
public class OrdersMeals {

    @EmbeddedId
    private OrdersMealsIdentity identity;
}
