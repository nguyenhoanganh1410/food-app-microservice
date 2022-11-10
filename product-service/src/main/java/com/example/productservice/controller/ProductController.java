package com.example.productservice.controller;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.productservice.dto.ProductReponse;
import com.example.productservice.dto.ProductRequest;
import com.example.productservice.entity.Product;
import com.example.productservice.service.ProductService;

@RestController
@RequestMapping("/v1/api/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Product createProduct(@RequestBody ProductRequest productRequest) {
		//productService.create(productRequest);
		System.out.println("seccess");
		return productService.create(productRequest);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ProductReponse getProductByID(@PathVariable("id") String id) {
		System.out.println(id);
		ProductReponse p = productService.getProductById(id);
		if (p == null) {
			throw new ProductNotFoundException("Product id not found: "
					+ " " + id);
		}
		return p;
		
	}
	//http://localhost:8080/v1/api/products?page=0&size=20
	//http://localhost:8080/v1/api/products?category=pizza&page=0&size=20
	  @GetMapping()
	  public ResponseEntity<Map<String, Object>> findByCategory(
		 @RequestParam(required = false) String category,
		 @RequestParam(required = false) String name,
	      @RequestParam(defaultValue = "0") int page,
	      @RequestParam(defaultValue = "20") int size){
		 
		 return productService.getListProduct(page, size, category, name);
	  }
	  
	  
	  @PutMapping("/{id}")
	  public ResponseEntity<Product> updateProduct(@PathVariable("id") String id, @RequestBody ProductRequest productRequest) {
	    ProductReponse productData = productService.getProductById(id);
	    if (productData == null) {
			throw new ProductNotFoundException("Product id not found: "
					+ " " + id);
		}
	    
	    //set data
	    productData.setName(productRequest.getName());
	    productData.setDesc(productRequest.getDesc());
	    productData.setPrice(productRequest.getPrice());
	    productData.setImages(productRequest.getImages());
	    productData.setCategory(productRequest.getCategory());
	    
	    Product p = productService.updateProduct(productData, id);
	    
	    
	   return  new ResponseEntity<>(p, HttpStatus.OK);
	   
	  }
	
	
}
