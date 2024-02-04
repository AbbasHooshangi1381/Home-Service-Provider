package com.example.springbootfinal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "i can not create picture !")
public class NotCreateException extends RuntimeException {

    public NotCreateException(String message) {
        super(message);
    }


}
