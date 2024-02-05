package com.example.springbootfinal.exception;

import org.springframework.http.HttpStatus;

public class ApiException {
    private final String message;
    private final HttpStatus httpStatus;
 //   private final Throwable throwable;

    public ApiException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
     //   this.throwable = throwable;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }


}
