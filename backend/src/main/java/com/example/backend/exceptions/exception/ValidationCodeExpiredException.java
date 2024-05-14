package com.example.backend.exceptions.exception;

import org.springframework.http.HttpStatus;

public class ValidationCodeExpiredException extends BaseException{
    public ValidationCodeExpiredException() {
        super("Validation code expired try again", HttpStatus.NOT_FOUND);
    }
}
