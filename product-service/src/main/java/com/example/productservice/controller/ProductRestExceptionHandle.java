package com.example.productservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductRestExceptionHandle {
	@ExceptionHandler
	public ResponseEntity<ProductErrorResponse> handleException(ProductNotFoundException exc){
		ProductErrorResponse erro = new ProductErrorResponse();
		erro.setStatus(HttpStatus.NOT_FOUND.value());
		erro.setMessage(exc.getMessage());
		erro.setTimStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
	}
}
