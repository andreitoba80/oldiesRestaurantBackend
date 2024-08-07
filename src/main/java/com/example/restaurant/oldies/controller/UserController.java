package com.example.restaurant.oldies.controller;

import com.example.restaurant.oldies.dto.LoginDto;
import com.example.restaurant.oldies.dto.UpdateUserDto;
import com.example.restaurant.oldies.entity.User;
import com.example.restaurant.oldies.repository.UserRepository;
import com.example.restaurant.oldies.service.UserService;
import com.example.restaurant.oldies.utils.LoggedUser;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.example.restaurant.oldies.utils.Security.encryptPassword;

@RestController
@CrossOrigin
@AllArgsConstructor
public class UserController implements UserControllerResource {
    UserRepository userRepository;
    UserService userService;

    @Override
    public ResponseEntity<String> createUser(@RequestBody User user) throws Exception {
        user.setPassword(encryptPassword(user.getPassword()));
        userService.createUser(user);
        return ResponseEntity.status(200).body("Created Successfully...");
    }

    @Override
    public ResponseEntity<String> createEmployeeAccount(User user) throws Exception {
        if (LoggedUser.getInstance().getIsAdmin()) {
            user.setPassword(encryptPassword(user.getPassword()));
            userService.createUser(user);
            return ResponseEntity.status(200).body("Employee account created successfully");
        } else {
            return ResponseEntity.status(400).body("You don't have access to complete this operation...");
        }
    }

    @Override
    public ResponseEntity<User> login(LoginDto loginDto) throws Exception {
        loginDto.setPassword(encryptPassword(loginDto.getPassword()));
        var loggingUser = userRepository.checkUserLoginCredentials(loginDto.getUsername(), loginDto.getPassword());
        if (loggingUser != null) {
            LoggedUser loggedUser = LoggedUser.getInstance();
            loggedUser.setUsername(loggingUser.getUsername());
            loggedUser.setPassword(loggingUser.getPassword());
            loggedUser.setIsAdmin(loggingUser.getIsAdmin());
            return ResponseEntity.status(200).body(loggingUser);
        }
        throw new Exception("User does not exist...");
    }

    @Override
    public ResponseEntity<String> updateUser(Long userId, UpdateUserDto updateUserDto) {
        try {
            userService.updateUser(userId, updateUserDto);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("User does not exists...");
        }
        return ResponseEntity.status(200).body("User has been updated successfully...");
    }

    @Override
    public ResponseEntity<String> deleteUserById(Long userId) {
        try {
            userService.deleteUser(userId);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.toString());
        }
        return ResponseEntity.status(200).body("User has been deleted successfully...");
    }

}
