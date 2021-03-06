package com.barankosecki.dto;

import com.barankosecki.entities.Meal;
import lombok.Data;

import java.util.List;

@Data
public class MealsByCategoryDTO {

    private Integer categoryId;
    private String categoryName;
    private List<Meal> meals;
}
