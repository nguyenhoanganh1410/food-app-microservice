package com.example.RedisDemo.service;

import com.example.RedisDemo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
@Service
public class ProductRedisService {
    @Autowired
	private RedisTemplate<String, Product> redisTemplate;

	public Product saveProduct( Product product) {
System.out.println("product"+product);
		//redisTemplate.opsForValue().set(user.getId(), user);
		redisTemplate.opsForHash().put("Product", product.getId(), product);

		 return product;
	}




	public Object findById( String id) {
	    return  redisTemplate.opsForHash().get("Product", id);
	}

	public Map<Object,Object> getlist() {
		 return redisTemplate.opsForHash().entries("Product");

	}

	public String delelePoduct( String id) {

		redisTemplate.opsForHash().delete("Product",id);

		return  "Delete Product id: "+id +"completed";
	}

    public String deleleKey( ) {

        redisTemplate.delete("Product");

        return  "Delete Product completed";
    }
}
