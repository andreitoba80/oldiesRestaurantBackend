package com.example.restaurant.oldies.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@AllArgsConstructor
@NoArgsConstructor
@With
@Data
public class OrderStatusDto {
    /**
     * orderStatus represents the status of the order
     * orderStatus can take the following values:
     * 0 -> New Order
     * 1 -> Preparing Order
     * 2 -> Order Completed
     */
    private Integer orderStatus;
}
