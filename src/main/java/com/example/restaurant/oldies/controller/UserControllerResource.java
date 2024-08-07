package com.example.restaurant.oldies.controller;

import com.example.restaurant.oldies.dto.LoginDto;
import com.example.restaurant.oldies.dto.UpdateUserDto;
import com.example.restaurant.oldies.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/user/")
public interface UserControllerResource {

    @PostMapping(value = "/CreateUser")
    ResponseEntity<String> createUser(@RequestBody User user) throws Exception;

    @PostMapping(value = "/login")
    ResponseEntity<User> login(@RequestBody LoginDto loginDto) throws Exception;

    @PutMapping(value = "UpdateUser/{userId}")
    ResponseEntity<String> updateUser(@PathVariable Long userId, @RequestBody UpdateUserDto updateUserDto);

    @DeleteMapping(value = "/deleteUserById/{userId}")
    ResponseEntity<String> deleteUserById(@PathVariable Long userId);

    @PostMapping(value = "/createEmployeeAccount")
    ResponseEntity<String> createEmployeeAccount(@RequestBody User user) throws Exception;
}
