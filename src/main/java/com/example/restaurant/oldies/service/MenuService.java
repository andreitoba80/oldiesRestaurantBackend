package com.example.restaurant.oldies.service;

import com.example.restaurant.oldies.dto.UpdateDishStockDto;
import com.example.restaurant.oldies.entity.Menu;
import org.springframework.stereotype.Component;

@Component
public interface MenuService {
    void createDish(Menu menu) throws Exception;

    void deleteDish(String uniqueCode) throws Exception;

    void updateStock(String uniqueCode, UpdateDishStockDto updateDishStockDto) throws Exception;
}
