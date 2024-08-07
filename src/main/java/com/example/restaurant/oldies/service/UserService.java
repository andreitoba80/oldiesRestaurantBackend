package com.example.restaurant.oldies.service;

import com.example.restaurant.oldies.dto.UpdateUserDto;
import com.example.restaurant.oldies.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

    void createUser(User user) throws Exception;

    void updateUser(Long userId, UpdateUserDto updateUserDto) throws Exception;

    void deleteUser(Long userId) throws Exception;
}
