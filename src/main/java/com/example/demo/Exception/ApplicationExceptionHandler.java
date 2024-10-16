package com.example.demo.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {
    public ApplicationExceptionHandler() {
    }

    @ExceptionHandler({ApplicationException.class})
    public ResponseEntity<?> handleApplicationException(ApplicationException ex) {
        return new ResponseEntity(ex.getMessage(), ex.getHttpStatus());
    }
}
