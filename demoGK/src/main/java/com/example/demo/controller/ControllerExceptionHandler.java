package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(AppException exc){
       ErrorResponse erro = new ErrorResponse();
        erro.setStatus(HttpStatus.NOT_FOUND.value());
        erro.setMessage(exc.getMessage());
        erro.setTimStamp(System.currentTimeMillis());

        return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }
}
