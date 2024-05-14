package com.example.backend.exceptions.exception;

import org.springframework.http.HttpStatus;

public class PromotionRequestExistException extends BaseException{
    public PromotionRequestExistException() {
        super("There is already a request by that user", HttpStatus.ALREADY_REPORTED);
    }
}
