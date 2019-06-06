package com.barankosecki.enitties;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}

