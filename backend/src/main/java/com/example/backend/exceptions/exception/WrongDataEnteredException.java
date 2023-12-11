package com.example.backend.exceptions.exception;

public class WrongDataEnteredException extends RuntimeException {

    public WrongDataEnteredException(String message) {
        super(message);
    }
}
