package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestfulControllerAdvice {

    @ExceptionHandler(RestfulException.class)
    public ResponseEntity<Void> handleRestfulException(RestfulException e) {
        return new ResponseEntity<>(e.getStatus());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleNormalException(Throwable e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
