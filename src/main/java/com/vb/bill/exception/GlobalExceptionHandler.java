package com.vb.bill.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidFieldException.class)
    public ResponseEntity<ErrorDetails> handleInvalidFieldException(InvalidFieldException invalidFieldException) {
        String invalidFieldExceptionMessage = invalidFieldException.getMessage();
        ErrorDetails errorDetails = new ErrorDetails(invalidFieldExceptionMessage, LocalDateTime.now());
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
