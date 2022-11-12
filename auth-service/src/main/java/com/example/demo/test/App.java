//package com.example.demo.test;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.example.demo.entity.User;
//import com.example.demo.entity.UserRepository;
//
//@SpringBootApplication
//public class App implements CommandLineRunner {
//    public static void main(String[] args) {
//        SpringApplication.run(App.class, args);
//    }
//
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Khi chương trình chạy
//        // Insert vào csdl một user.
//        User user = new User();
//        user.setUserName("loda");
//        user.setPassWord(passwordEncoder.encode("loda"));
//        userRepository.save(user);
//        System.out.println(user);
//    }
//}
