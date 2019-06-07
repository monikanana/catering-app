package com.barankosecki.enitties;

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
    private Category category;
}
