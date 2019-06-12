package com.barankosecki.dto;

import lombok.Data;

import java.util.List;

@Data
public class SubscriptionFromClientDTO {

    private List<Integer> mealsId;
    private Integer locationId;
    private Integer customerId;

    //TODO

}
