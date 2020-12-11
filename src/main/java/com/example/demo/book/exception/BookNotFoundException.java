package com.example.demo.book.exception;

import com.example.demo.exception.RestfulException;
import org.springframework.http.HttpStatus;

public class BookNotFoundException extends RestfulException {

    public BookNotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }
}
