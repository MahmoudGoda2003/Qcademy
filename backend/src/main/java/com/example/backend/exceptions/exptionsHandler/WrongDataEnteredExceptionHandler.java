package com.example.backend.exceptions.exptionsHandler;

import com.example.backend.exceptions.exceptions.WrongDataEnteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class WrongDataEnteredExceptionHandler {

    @ExceptionHandler(WrongDataEnteredException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    Map<String, String> onWrongDataEnteredException(WrongDataEnteredException e) {
        Map<String, String> error = new HashMap<>();
        error.put("error message", e.getLocalizedMessage());
        return error;
    }
}
