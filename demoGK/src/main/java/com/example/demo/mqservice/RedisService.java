package com.example.demo.mqservice;

import com.example.demo.controller.AppException;
import com.example.demo.entity.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import org.springframework.transaction.annotation.Transactional;

//import javax.transaction.Transactional;
import java.util.Map;

@Repository
public class RedisService implements  UserDetailsService {
	 @Autowired
		private RedisTemplate<String, User> redisTemplate;
	 
	 
	 public RedisService(RedisTemplate<String, User> redisTemplate) {
		super();
		this.redisTemplate = redisTemplate;
	}


	public void addUser(User user) {
		 redisTemplate.opsForHash().put("User", user.getId(), user);
	 }
	@Transactional
	public UserDetails getUserById(Long id) {

			User user = (User) redisTemplate.opsForHash().get("User", id);
		if(user ==null){
			throw  new AppException(404,"user not found");
		}

		return new CustomUserDetails(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Map<Object, Object> a = redisTemplate.opsForHash().entries("User");
		for(Object value: a.values()){
			User user = (User) value;
			if(user.getUserName().contains(username)){
				return new CustomUserDetails(user);
			}


		}

		throw  new AppException(404,"user not found");
	}
}
