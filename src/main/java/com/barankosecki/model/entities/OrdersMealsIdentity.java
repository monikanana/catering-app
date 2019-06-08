package com.barankosecki.model.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class OrdersMealsIdentity implements Serializable {

    @Column(name = "id_order")
    private Integer orderId;

    @Column(name = "id_meal")
    private Integer mealId;
}
