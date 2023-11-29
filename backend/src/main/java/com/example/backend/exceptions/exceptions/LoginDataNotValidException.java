package com.example.backend.exceptions.exceptions;

public class LoginDataNotValidException extends RuntimeException {

    public LoginDataNotValidException(String message) {
        super(message);
    }
}