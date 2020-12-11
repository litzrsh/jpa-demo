package com.example.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class RestfulException extends Exception {

    private final HttpStatus status;

    public RestfulException(HttpStatus status) {
        this.status = status;
    }
}
