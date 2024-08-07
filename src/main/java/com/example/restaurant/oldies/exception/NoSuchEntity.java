package com.example.restaurant.oldies.exception;

public class NoSuchEntity extends Exception {
    public NoSuchEntity(String entityName) {
        super("Entity \"" + entityName + " does not exist...");
    }

}
