package com.example.restaurant.oldies.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@ToString
public class Order {
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "order_dish",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private Set<Menu> dishes = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;
    private Float totalCost;
    /**
     * orderStatus represents the status of the order
     * orderStatus can take the following values:
     * 0 -> New Order
     * 1 -> Preparing Order
     * 2 -> Order Completed
     */
    private Integer orderStatus = 0;
    private LocalDateTime orderDateTime;
}
