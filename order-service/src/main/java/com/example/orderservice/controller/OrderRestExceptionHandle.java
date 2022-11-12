package com.example.orderservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderRestExceptionHandle {
	@ExceptionHandler
	public ResponseEntity<OrderErrorResponse> handleException(OrderNotFoundException exc){
		OrderErrorResponse erro = new OrderErrorResponse();
		erro.setStatus(HttpStatus.NOT_FOUND.value());
		erro.setMessage(exc.getMessage());
		erro.setTimStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
	}
}
