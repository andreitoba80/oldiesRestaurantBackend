package com.example.restaurant.oldies.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu")
@ToString
public class Menu {
    @ManyToMany(mappedBy = "dishes")
    Set<Order> orderSet;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dish_id")
    private Long dishId;
    private String dishName;
    private Float price;
    private Integer stock;
    @Column(unique = true)
    private String uniqueCode;
}
