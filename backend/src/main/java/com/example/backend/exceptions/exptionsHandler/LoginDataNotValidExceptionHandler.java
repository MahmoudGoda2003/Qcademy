package com.example.backend.exceptions.exptionsHandler;

import com.example.backend.exceptions.exception.LoginDataNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class LoginDataNotValidExceptionHandler {

    @ExceptionHandler(LoginDataNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    Map<String, String> onLoginDataNotValidException(Exception e) {
        Map<String, String> error = new HashMap<>();
        error.put("error message", e.getLocalizedMessage());
        return error;
    }
}
