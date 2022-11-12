package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

//import org.hibernate.engine.query.spi.sql.NativeSQLQueryCollectionReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.entity.CustomUserDetails;
import com.example.demo.entity.User;
//import com.example.demo.entity.UserRepository;

import com.example.demo.jwt.JwtAuthenticationFilter;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.mqservice.RedisService;
import com.example.demo.payload.LoginRequest;
import com.example.demo.payload.LoginResponse;
import com.example.demo.payload.RandomStuff;
import com.fasterxml.jackson.core.JsonProcessingException;

//
import ch.qos.logback.core.net.LoginAuthenticator;

@RestController
@RequestMapping()
public class LodaRestController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RedisService redisService;

    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
	private JwtAuthenticationFilter authenticationFilter;
   
    @PostMapping(value = "/register")
    public User Register( @Valid @RequestBody LoginRequest loginRequest) {
    		try {
    			System.out.println("user: "+loginRequest);
                User user = new User(loginRequest.getUsername(),(passwordEncoder.encode(loginRequest.getPassword())));
//              user.setUserName(loginRequest.getUsername());
              //user.setPassWord(passwordEncoder.encode(loginRequest.getPassword()));
              UserDetails userDetails = redisService.loadUserByUsername(user.getUserName());
              System.out.println("details "+userDetails);
              if(userDetails!=null) {
            	  return new User("Loi", "Loi");
              }else {
    			
            	 redisService.addUser(user);
          		//System.out.println("user"+user);
            	//userRepository.save(user);
            	System.out.println("new user"+user);

//            
            return user;
            }
			} catch (Exception e) {
				return new User(e.getMessage());
			}
    		
    }

    @PostMapping(value = "/login", 	consumes = "application/json")
    public String authenticateUser( @Valid @RequestBody LoginRequest loginRequest) {
    		System.out.println("user: "+loginRequest);
            User user = new User();
          user.setUserName(loginRequest.getUsername());
          user.setPassWord(passwordEncoder.encode(loginRequest.getPassword()));
        
		
      //  userRepository.save(user);
          System.out.println("new user"+user);

//         Xác thực từ username và password.
    		Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication((org.springframework.security.core.Authentication) authentication);

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((CustomUserDetails) ((org.springframework.security.core.Authentication) authentication).getPrincipal());
          
        return "jwt "+ jwt;
        
    }

    // Api /api/random yêu cầu phải xác thực mới có thể request
    @GetMapping("/random")
    public String randomStuff(){
    	try {
    		return "JWT Hợp lệ mới có thể thấy được message này";
		} catch (Exception e) {
			return e.getMessage();
		}
    	//System.out.println("dat");
    	
    }
    @GetMapping("/test")
    public String test(){

            return "test jwt";

        //System.out.println("dat");

    }
    @GetMapping("/user")
    public UserDetails getUserByJwt(HttpServletRequest request) {
    	
    	
        	String jwt = authenticationFilter.getJwtFromRequest(request);
        	 if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                 // Lấy id user từ chuỗi jwt
                 Long userId = tokenProvider.getUserIdFromJWT(jwt);
                 // Lấy thông tin người dùng từ id
                 UserDetails userDetails =  redisService.getUserById(userId);
                 if(userDetails != null) {
                	 	return userDetails;
                	 
                	 }
                 
                 }
        	 return null;
        
    }
//    @PostMapping("/mess")
//    public String sendMess(@RequestBody String mess ) throws JsonProcessingException {
//    		
//    	producerService.sendToTopic("myTopic", mess);
//    	
//    	
//    	return mess;
//    	
//    }

}
