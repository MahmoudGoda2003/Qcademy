package com.example.backend.exceptions.exceptions;

public class WrongDataEnteredException extends RuntimeException {

    public WrongDataEnteredException(String message) {
        super(message);
    }
}
