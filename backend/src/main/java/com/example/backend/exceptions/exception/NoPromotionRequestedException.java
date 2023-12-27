package com.example.backend.exceptions.exception;

import org.springframework.http.HttpStatus;

public class NoPromotionRequestedException extends BaseException{
    public NoPromotionRequestedException() {
        super("No promotion requested", HttpStatus.NOT_FOUND);
    }
}
