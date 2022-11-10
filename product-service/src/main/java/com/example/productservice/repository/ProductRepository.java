package com.example.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.productservice.entity.Product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductRepository extends MongoRepository<Product, String> {
	Page<Product> findByCategory(String category, Pageable pageable);

	  Page<Product> findByCategoryContainingIgnoreCase(String title, Pageable pageable);
	  
	  Page<Product> findByNameContaining(String title, Pageable pageable);
}
