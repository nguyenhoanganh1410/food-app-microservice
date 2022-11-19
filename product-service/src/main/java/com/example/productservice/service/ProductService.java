
package com.example.productservice.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import com.example.productservice.dto.ProductReponse;
import com.example.productservice.dto.ProductRequest;
import com.example.productservice.entity.Product;
import com.example.productservice.repository.ProductRepository;

@Service
@Transactional
public class ProductService {
	private ProductRepository productRepository;


	@Autowired
	private RestTemplate restTemplate;
		
	
	public ProductService(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}
	

	public Product create(ProductRequest productRequest) {
		Product p = new Product(productRequest.getName(), productRequest.getDesc(), productRequest.getPrice(), productRequest.getCategory(),productRequest.getStock(), productRequest.getImages());
		
		Product product = productRepository.save(p);
	//	System.out.println(product.toString());
		//call api save into inventory service
		// request url
		String url = "http://INVENTORY-SERVICE/v1/api/inventory";

		// create headers
		HttpHeaders headers = new HttpHeaders();
		// set `content-type` header
		headers.setContentType(MediaType.APPLICATION_JSON);
		// set `accept` header
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		
		// request body parameters
		Map<String, Object> map = new HashMap<>();
		map.put("idProduct", product.getId());
		map.put("quatity", product.getStock());
		map.put("numberOfProductSoild",0);

		

		// build the request
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

		// send POST request
		ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
		
		// check response
		if (response.getStatusCode() == HttpStatus.CREATED) {
		    System.out.println("Request Successful");
		    System.out.println(response.getBody());
		} else {
		    System.out.println("Request Failed");
		    System.out.println(response.getStatusCode());
		}
		
		return p;
	}

	@Cacheable(value = "ProductReponse", key = "#id")
	public ProductReponse getProductById(String id) {
		System.out.println("product ppp");
		// TODO Auto-generated method stub
		Optional<Product> p = productRepository.findById(id);
		if(p.isEmpty()) {
			return null;
		}
		
		Product product = p.get();
		System.out.println("product" + p);
		ProductReponse pReponse = new ProductReponse(product.getId(), product.getName(), 
				product.getDesc(), product.getPrice(), product.getCategory(),product.getImages());
		return pReponse;
		
	}
	
	
	
	public ResponseEntity<Map<String, Object>> getListProduct(Integer page, Integer size, String category, String name) {
		  try {
		      List<Product> tutorials = new ArrayList<Product>();
		      Pageable paging = PageRequest.of(page, size);
		      System.out.println("category" + category);
		      System.out.println("name" + name);
		      Page<Product> pageTuts = null;
		      if (category == null && name == null)
		        pageTuts = productRepository.findAll(paging);  
		      else if(name == null)
		        pageTuts = productRepository.findByCategoryContainingIgnoreCase(category, paging);
		      else if( category == null)
		    	pageTuts = productRepository.findByNameContaining(name, paging);

		      tutorials = pageTuts.getContent();
		     
		      Map<String, Object> response = new HashMap<>();
		      response.put("products", tutorials);
		      response.put("currentPage", pageTuts.getNumber());
		      response.put("totalItems", pageTuts.getTotalElements());
		      response.put("totalPages", pageTuts.getTotalPages());

		      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		     
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
		
	}


	 @CachePut(value = "Product",key = "#id")
	public Product updateProduct(ProductReponse productReponse, String id, Integer stock) {
		Product p = new Product(id, productReponse.getName(), productReponse.getDesc(), productReponse.getPrice(), productReponse.getCategory(),stock ,productReponse.getImages());
		productRepository.save(p);
		
		return p;
	}
	




	
}
