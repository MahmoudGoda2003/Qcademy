package com.example.backend.exceptions.exception;

import org.springframework.http.HttpStatus;

public class EmailAlreadyExistsException extends BaseException{
    public EmailAlreadyExistsException() {
        super("Email already exists", HttpStatus.NOT_ACCEPTABLE);
    }
}
