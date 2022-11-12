package com.example.orderservice.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.orderservice.entity.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {
	//List<Order> findByCustomerEmail(String customerEmail);
	
	Page<Order> findByCustomerEmail(String customerEmail, Pageable pageable);
}
