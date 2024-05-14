package com.example.backend.exceptions.exptionsHandler;

import com.example.backend.exceptions.exception.Error;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
public class ConstraintViolationHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handelConstrainViolation(MethodArgumentNotValidException e) {
        Error error = Error.builder()
                .message(Arrays.toString(Objects.requireNonNull(e.getDetailMessageArguments())))
                .status(HttpStatus.BAD_REQUEST).path(String.valueOf(e.getStackTrace()[0]))
                .timestamp(LocalDate.now())
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
