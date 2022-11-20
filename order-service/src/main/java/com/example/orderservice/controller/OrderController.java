package com.example.orderservice.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.orderservice.dto.OrderReponse;
import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/v1/api/orders")
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	@PostMapping
    @CircuitBreaker(name = "inventory",  fallbackMethod = "fallbackMethod")
	@Retry(name = "inventory", fallbackMethod = "fallbackMethod")
	@RateLimiter(name = "inventory", fallbackMethod = "rateLimiterMethod")
	public String create(@RequestBody OrderRequest orderRequest) {
		service.createOrder(orderRequest);
		//return new ResponseEntity<>(orderRequest, HttpStatus.CREATED);
		return "Place an order sucessfully!!";
	}
	
	public String fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException) {
        return  "Oops! Something went wrong, please order after some time!";
    }
	public String rateLimiterMethod(OrderRequest orderRequest, RuntimeException runtimeException) {
        return  "Oops! order service does not permit futher calls, please order after some time!";
    }
	
	@GetMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public OrderReponse getOrdertByID(@PathVariable("id") Long id) {
		System.out.println(id);
		OrderReponse orderReponse = service.getOrderByID(id);
		if (orderReponse == null) {
			throw new OrderNotFoundException("Order id not found: "
					+ " " + id);
		}
	
		
		
		return orderReponse;
	}
	
	//http://localhost:8088/v1/api/orders?customerEmail&page=0&size=20
	
		  @GetMapping()
		  public ResponseEntity<Map<String, Object>> getOrdersByEmail(
			 @RequestParam(required = false) String customerEmail,
		      @RequestParam(defaultValue = "0") int page,
		      @RequestParam(defaultValue = "20") int size){
			 
			 return service.getOrdersByEmail(page, size, customerEmail);
		  }
	@GetMapping("/hello")
	public String hello() {
		return "hello ordres";
	}
}
