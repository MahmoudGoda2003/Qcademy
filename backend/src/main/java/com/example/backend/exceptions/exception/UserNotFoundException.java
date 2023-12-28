package com.example.backend.exceptions.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException {

    public UserNotFoundException() {
        super("User Was Not Found", HttpStatus.NOT_FOUND);
    }
}
