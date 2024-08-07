package com.example.restaurant.oldies.controller;

import com.example.restaurant.oldies.dto.UpdateDishStockDto;
import com.example.restaurant.oldies.entity.Menu;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/menu")
public interface MenuControllerResource {
    @PostMapping(value = "/createDish")
    ResponseEntity<String> createDish(@RequestBody Menu menu) throws Exception;

    @DeleteMapping(value = "/deleteDish/{uniqueCode}")
    ResponseEntity<String> deleteDishByUniqueCode(@PathVariable String uniqueCode);

    @PostMapping(value = "/updateStock/{uniqueCode}")
    ResponseEntity<String> updateStock(@PathVariable String uniqueCode, @RequestBody UpdateDishStockDto updateDishStockDto);
}
