package com.example.backend.exceptions.exception;

import org.springframework.http.HttpStatus;

public class EmailNotValidException extends BaseException{
    public EmailNotValidException() {
        super("Email not valid, please try again", HttpStatus.NOT_ACCEPTABLE);
    }
}
