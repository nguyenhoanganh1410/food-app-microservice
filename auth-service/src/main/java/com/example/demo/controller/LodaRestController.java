package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

//import org.hibernate.engine.query.spi.sql.NativeSQLQueryCollectionReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import com.example.demo.entity.CustomUserDetails;
import com.example.demo.entity.User;
//import com.example.demo.entity.UserRepository;

import com.example.demo.jwt.JwtAuthenticationFilter;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.mqservice.RedisService;
import com.example.demo.payload.LoginRequest;
import com.example.demo.payload.LoginResponse;
import com.example.demo.payload.RandomStuff;

//

import java.util.List;
import java.util.Map;

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

    //body username,password
    @PostMapping(value = "/register")
    public User Register( @Valid @RequestBody LoginRequest loginRequest) {
    		try {
    			System.out.println("user: "+loginRequest);
                User user = new User(loginRequest.getUsername(),(passwordEncoder.encode(loginRequest.getPassword())));
//              user.setUserName(loginRequest.getUsername());
              //user.setPassWord(passwordEncoder.encode(loginRequest.getPassword()));

                System.out.println("userName: "+user.getUserName());
              UserDetails userDetails = redisService.loadUserByUsername(loginRequest.getUsername());
              System.out.println("details "+userDetails);
              if(userDetails!=null) {

                  throw new AppException(404, " user already exist");
              }else {

            	 redisService.addUser(user);
          		System.out.println("user"+user);

            	System.out.println("new user"+user);

//            
            return user;
            }
			} catch (Exception e) {
				throw  new AppException(404,e.getMessage());
			}
    		
    }
    //body username,password
    @PostMapping(value = "/login", 	consumes = "application/json")
    public LoginResponse authenticateUser( @Valid @RequestBody LoginRequest loginRequest) {
        try {
            System.out.println("user: "+loginRequest);
            User user = new User();
            user.setUserName(loginRequest.getUsername());
            user.setPassWord(passwordEncoder.encode(loginRequest.getPassword()));


            //  userRepository.save(user);
            System.out.println("new user"+user);

//         X??c th???c t??? username v?? password.
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            // N???u kh??ng x???y ra exception t???c l?? th??ng tin h???p l???
            // Set th??ng tin authentication v??o Security Context
            SecurityContextHolder.getContext().setAuthentication((org.springframework.security.core.Authentication) authentication);

            // Tr??? v??? jwt cho ng?????i d??ng.
            String jwt = tokenProvider.generateToken((CustomUserDetails) ((org.springframework.security.core.Authentication) authentication).getPrincipal());

            return new LoginResponse(jwt);
        }catch (Exception e){
            throw  new AppException(404,"Username or Password Invalid");
        }

        
    }

    // Api /api/random  test y??u c???u ph???i x??c th???c m???i c?? th??? request
    @GetMapping("/test")
    public RandomStuff randomStuff(){

    	return  new RandomStuff("JWT hop le moi thay duoc nha bay");
    	
    }
    @GetMapping("/random")
    public String test(){

            return "test jwt";



    }
    //Authorization : bearer token ----jwt
    //get user with token
    @GetMapping("/user")
    public UserDetails getUserByJwt(HttpServletRequest request) {
    	
    	
        	String jwt = authenticationFilter.getJwtFromRequest(request);
        	 if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                 // L???y id user t??? chu???i jwt
                 Long userId = tokenProvider.getUserIdFromJWT(jwt);
                 // L???y th??ng tin ng?????i d??ng t??? id
                 UserDetails userDetails =  redisService.getUserById(userId);
                 if(userDetails != null) {
                	 	return userDetails;
                	 
                	 }
                 
                 }
            throw new AppException(404, " User not found");
        
    }
    //body id,username,password
    @PutMapping("user/update")
    public  RandomStuff updateUser( @RequestBody LoginRequest loginRequest){
        User user = new User();
        user.setId(loginRequest.getId());
        user.setUserName(loginRequest.getUsername());

        String passEncoder = passwordEncoder.encode(loginRequest.getPassword());
        user.setPassWord(passEncoder);
        redisService.updateUser(user);
        return new RandomStuff("Update user successfully "+passEncoder);
    }
    //getlistuser permitAll for test
    @GetMapping("/listuser")
    public List<User> getlist() {
        return  redisService.getlist();
    }

    @PostMapping ("/validateToken")
    public ResponseEntity<User> getUserByJwtRequest(HttpServletRequest request) {


        String jwt = authenticationFilter.getJwtFromRequest(request);
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            // L???y id user t??? chu???i jwt
            Long userId = tokenProvider.getUserIdFromJWT(jwt);
            // L???y th??ng tin ng?????i d??ng t??? id
           User userDetails =  redisService.getUserByIdUser(userId);
            if(userDetails != null) {
                return ResponseEntity.ok(userDetails);

            }

        }
        throw new AppException(404, " User not found");

    }
}
