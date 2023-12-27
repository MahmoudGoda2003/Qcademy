package com.example.backend.exceptions.exception;

import org.springframework.http.HttpStatus;

public class LoginDataNotValidException extends BaseException {

    public LoginDataNotValidException() {
        super("password or email isn't valid", HttpStatus.NOT_ACCEPTABLE);
    }
}