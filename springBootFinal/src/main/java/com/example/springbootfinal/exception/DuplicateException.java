package com.example.springbootfinal.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Resource already exists")
public class DuplicateException extends RuntimeException {

    public DuplicateException(String message) {
        super(message);
    }

}
