package com.example.restaurant.oldies.controller;

import com.example.restaurant.oldies.dto.UpdateDishStockDto;
import com.example.restaurant.oldies.entity.Menu;
import com.example.restaurant.oldies.service.MenuService;
import com.example.restaurant.oldies.utils.LoggedUser;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@AllArgsConstructor
public class MenuController implements MenuControllerResource {
    private MenuService menuService;

    @Override
    public ResponseEntity<String> createDish(@RequestBody Menu menu) throws Exception {

        if (LoggedUser.getInstance().getIsAdmin()) {
            menuService.createDish(menu);
            return ResponseEntity.status(200).body("Dish added successfully...");
        }
        return ResponseEntity.status(400).body("You do not have enough right in order to add a new dish...");
    }

    @Override
    public ResponseEntity<String> deleteDishByUniqueCode(String uniqueCode) {
        try {
            menuService.deleteDish(uniqueCode);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.toString());
        }
        return ResponseEntity.status(200).body("Dish has been deleted successfully...");

    }

    @Override
    public ResponseEntity<String> updateStock(String uniqueCode, UpdateDishStockDto updateDishStockDto) {
        try {
            menuService.updateStock(uniqueCode, updateDishStockDto);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("This dish does not exists or you do not have rights to complete this request...");
        }
        return ResponseEntity.status(200).body("This dish's  stock has been updated successfully...");
    }
}
