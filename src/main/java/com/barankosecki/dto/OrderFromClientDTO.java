package com.barankosecki.dto;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class OrderFromClientDTO {

    private List<Integer> mealsId;
    private Integer locationId;
    private Integer customerId;
    private Date date;
}
