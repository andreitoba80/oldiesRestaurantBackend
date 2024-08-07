package com.example.restaurant.oldies.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@AllArgsConstructor
@NoArgsConstructor
@Data
@With
public class DishOrderDto {
    private String uniqueCode;
    private Integer quantity;
}
