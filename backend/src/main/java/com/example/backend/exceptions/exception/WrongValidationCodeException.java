package com.example.backend.exceptions.exception;

import org.springframework.http.HttpStatus;

public class WrongValidationCodeException extends BaseException{
    public WrongValidationCodeException() {
        super("Wrong code, please try again", HttpStatus.NOT_ACCEPTABLE);
    }
}
