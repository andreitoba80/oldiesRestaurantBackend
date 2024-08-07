package com.example.restaurant.oldies.exception;

public class NotEnoughStockException extends Exception {
    public NotEnoughStockException(String dishName) {
        super("There is not enough stock for dish " + dishName);
    }

}
