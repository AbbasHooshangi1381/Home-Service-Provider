package com.example.springbootfinal.exception;

public class NotEnoughCreditException extends RuntimeException{

    public NotEnoughCreditException(String message) {
        super(message);
    }
}
