package com.example.springbootfinal.exception;

public class InvalidDateException extends RuntimeException {

    public InvalidDateException(String message) {
        super(message);
    }

}
