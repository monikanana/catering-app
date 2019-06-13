package com.barankosecki.dto;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class SubscriptionFromClientDTO {

    private List<Integer> mealsId;
    private Integer locationId;
    private Integer customerId;
    private List<Date> dates; // dates of delivery in the next week
}
