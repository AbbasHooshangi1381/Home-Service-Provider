package com.example.springbootfinal.exception;


public class NotValidException extends RuntimeException {

    public NotValidException(String message) {
        super(message);
    }
}
