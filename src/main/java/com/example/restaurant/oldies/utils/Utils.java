package com.example.restaurant.oldies.utils;

public class Utils {
    public static Boolean isAdmin() {
        return LoggedUser.getInstance().getIsAdmin();
    }
}
