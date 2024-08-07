package com.example.restaurant.oldies.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@AllArgsConstructor
@NoArgsConstructor
@Data
@With
public class UpdateUserDto {
    String firstName;
    String lastName;
    String email;
    String username;
    String password;
}
