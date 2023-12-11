package com.example.backend.exceptions.exception;

public class LoginDataNotValidException extends RuntimeException {

    public LoginDataNotValidException(String message) {
        super(message);
    }
}