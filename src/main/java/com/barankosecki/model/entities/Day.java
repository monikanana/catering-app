package com.barankosecki.model.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "days")
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;
}
