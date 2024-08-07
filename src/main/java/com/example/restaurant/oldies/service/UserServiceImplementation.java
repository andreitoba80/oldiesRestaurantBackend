package com.example.restaurant.oldies.service;

import com.example.restaurant.oldies.dto.UpdateUserDto;
import com.example.restaurant.oldies.entity.User;
import com.example.restaurant.oldies.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

import static com.example.restaurant.oldies.utils.Security.encryptPassword;

@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    @Override
    public void createUser(User user) throws Exception {
        try {
            if (userRepository.findByEmailOrUsername(user.getEmail(), user.getUsername()) == null) {
                userRepository.save(user);
            } else {
                throw new Exception("User with the same username or email address already exists...");
            }
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    @Override
    public void updateUser(Long userId, UpdateUserDto updateUserDto) throws Exception {
        var userData = userRepository.findUserById(userId);
        if (userData != null) {
            userData.setFirstName(updateUserDto.getFirstName());
            userData.setLastName(updateUserDto.getLastName());
            userData.setEmail(updateUserDto.getEmail());
            userData.setUsername(updateUserDto.getUsername());
            try {
                userData.setPassword(encryptPassword(updateUserDto.getPassword()));
            } catch (NoSuchAlgorithmException e) {
                throw new Exception(e.toString());
            }

            userRepository.save(userData);
        } else {
            throw new Exception("User does not exist...");
        }
    }

    @Override
    public void deleteUser(Long userId) throws Exception {
        var userData = userRepository.findUserById(userId);

        if (userData != null) {
            userRepository.delete(userData);
        } else {
            throw new Exception("User does not exist....");
        }
    }
}
