package com.example.RedisDemo.controller;

import java.util.List;
import java.util.Map;

import com.example.RedisDemo.entity.Product;
import com.example.RedisDemo.service.ProductRedisService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {
	//static Gson gson = new Gson();
	@Autowired
	private ProductRedisService productRedisService;

	@PostMapping("/save")
	public Product saveUser(@RequestBody Product p) {

		//redisTemplate.opsForValue().set(user.getId(), user);
	 return 	productRedisService.saveProduct(p);

	}

//	@PostMapping("/uservalue")
//	public User saveUserValue(@RequestBody User user) {
//
//		redisTemplate.opsForValue().set(user.getId(), user);
////		redisTemplate.opsForHash().put("User", user.getId(), user);
//		System.out.println("user"+user);
//		 return user;
//	}
//	@GetMapping("/uservalue/{id}")
//	public User getUserValue(@PathVariable("id") String id) {
//
////
//		 return redisTemplate.opsForValue().get(id);
//	}
//	@GetMapping("/userhash/{id}")
//	public Object findById(@PathVariable("id") String id) {
//	    return  redisTemplate.opsForHash().get("User", id);
//	}
	@GetMapping("/list")
	public Map<Object,Object> getlist() {

		return productRedisService.getlist();
	}

//	@DeleteMapping("/uservalue/{id}")
//	public Boolean deleteUser( @PathVariable("id") String id) {
//	    return  redisTemplate.delete(id);
//	}
//	@DeleteMapping("/userhash/{id}")
//	public String deleleUseHash(@PathVariable("id") String id) {
//
//		redisTemplate.delete("User");
//
//		return  "Delete user id: "+id +"completed";
//	}

}
