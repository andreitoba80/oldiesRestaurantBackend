package com.example.restaurant.oldies.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@With
public class OrderSubmissionDto {
    private List<DishOrderDto> dishes;
}
