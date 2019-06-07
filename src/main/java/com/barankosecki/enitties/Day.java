package com.barankosecki.enitties;

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
