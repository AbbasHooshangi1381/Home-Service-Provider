package com.example.springbootfinal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = DuplicateException.class)
    public ResponseEntity<Object> handleApiDuplicateException(DuplicateException d){
        HttpStatus alreadyReported=HttpStatus.NOT_ACCEPTABLE;
         ApiException apiException = new ApiException(
                d.getMessage(),
                alreadyReported

        );
         return new ResponseEntity<>(apiException,alreadyReported);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object>handleNoyFoundException(NotFoundException n){
        HttpStatus notFound=HttpStatus.NOT_FOUND;
        ApiException apiException=new ApiException(
                n.getMessage(),
                notFound

        );
        return new ResponseEntity<>(apiException,notFound);
    }

    @ExceptionHandler(value = NotValidException.class)
    public ResponseEntity<Object>handleNotValidException(NotValidException n){
        HttpStatus notValid=HttpStatus.NOT_ACCEPTABLE;
        ApiException apiException=new ApiException(
                n.getMessage(),
                notValid

        );
        return new ResponseEntity<>(apiException,notValid);
    }

    @ExceptionHandler(value = NotCreateException.class)
    public ResponseEntity<Object>handleNotCreateException(NotCreateException n){
        HttpStatus notValid=HttpStatus.NOT_MODIFIED;
        ApiException apiException=new ApiException(
                n.getMessage(),
                notValid

        );
        return new ResponseEntity<>(apiException,notValid);
    }

    @ExceptionHandler(value = InvalidDateException.class)
    public ResponseEntity<Object>handleInvalidDateException(InvalidDateException n){
        HttpStatus notValid=HttpStatus.NOT_ACCEPTABLE;
        ApiException apiException=new ApiException(
                n.getMessage(),
                notValid

        );
        return new ResponseEntity<>(apiException,notValid);
    }

    @ExceptionHandler(value = NotEnoughCreditException.class)
    public ResponseEntity<Object>handleNotEnoughCreditException(NotEnoughCreditException n){
        HttpStatus notValid=HttpStatus.BAD_REQUEST;
        ApiException apiException=new ApiException(
                n.getMessage(),
                notValid

        );
        return new ResponseEntity<>(apiException,notValid);
    }
}
