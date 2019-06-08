package com.barankosecki.model.entities;

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

    private Integer weight;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Category category;
}
