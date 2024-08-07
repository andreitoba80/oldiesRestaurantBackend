package com.example.restaurant.oldies.exception;

public class NoRightsException extends Exception {
    public NoRightsException() {
        super("You do not have the necessary rights to perform this action...");
    }
}
