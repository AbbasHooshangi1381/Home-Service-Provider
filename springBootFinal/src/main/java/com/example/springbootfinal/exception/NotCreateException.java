package com.example.springbootfinal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class NotCreateException extends RuntimeException {

    public NotCreateException(String message) {
        super(message);
    }
    public NotCreateException(String message, Throwable cause) {
        super(message, cause);
    }

}
