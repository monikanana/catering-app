package com.barankosecki.enitties;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


import javax.persistence.*;

@Entity
@Data
@Table(name = "meals")
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Double price;

    @Column(name = "is_top_meal")
    private Boolean isTopMeal;

    private Integer weight;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Category category;
}
