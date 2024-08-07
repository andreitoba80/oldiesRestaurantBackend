package com.example.restaurant.oldies.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class LoggedUser {
    private static LoggedUser instance;
    private String username;
    private String password;
    private Boolean isAdmin;

    public static LoggedUser getInstance() {
        if (instance == null) {
            instance = new LoggedUser("defaultUsername", "defaultPassword", false);
        }
        return instance;
    }

}
