package com.example.springbootfinal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "it is not valid")
public class NotValidException extends RuntimeException {

    public NotValidException(String message) {
        super(message);
    }


}
