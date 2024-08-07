package com.example.restaurant.oldies.service;

import com.example.restaurant.oldies.dto.UpdateDishStockDto;
import com.example.restaurant.oldies.entity.Menu;
import com.example.restaurant.oldies.exception.NoRightsException;
import com.example.restaurant.oldies.repository.MenuRepository;
import com.example.restaurant.oldies.utils.LoggedUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MenuServiceImplementation implements MenuService {
    private final MenuRepository menuRepository;

    @Override
    public void createDish(Menu menu) throws Exception {
        try {
            if (menuRepository.findByUniqueCode(menu.getUniqueCode()) == null) {
                menuRepository.save(menu);
            } else {
                throw new Exception("This dish already exist...");
            }
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    @Override
    public void deleteDish(String uniqueCode) throws Exception {
        if (LoggedUser.getInstance().getIsAdmin()) {
            var dishData = menuRepository.findByUniqueCode(uniqueCode);
            if (dishData != null) {
                menuRepository.delete(dishData);
            } else throw new Exception("This dish does not exist...");
        } else {
            throw new NoRightsException();
        }
    }

    @Override
    public void updateStock(String uniqueCode, UpdateDishStockDto updateDishStockDto) throws Exception {
        if (LoggedUser.getInstance().getIsAdmin()) {
            var dishData = menuRepository.findByUniqueCode(uniqueCode);
            if (dishData != null) {
                dishData.setStock(updateDishStockDto.getStock());
                menuRepository.save(dishData);
            } else {
                throw new Exception("This dish does not exist in the menu...");
            }
        } else {
            throw new Exception("You are not allowed to change the stock of any dishes...");
        }
    }
}
