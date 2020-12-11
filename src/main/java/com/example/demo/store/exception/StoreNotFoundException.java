package com.example.demo.store.exception;

import com.example.demo.exception.RestfulException;
import org.springframework.http.HttpStatus;

public class StoreNotFoundException extends RestfulException {

    public StoreNotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }
}
