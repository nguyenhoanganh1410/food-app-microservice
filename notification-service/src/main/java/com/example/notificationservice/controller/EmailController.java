package com.example.notificationservice.controller;
//Java Program to Create Rest Controller that
//Defines various API for Sending Mail



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.notificationservice.entity.EmailDetails;
import com.example.notificationservice.service.EmailService;

//Annotation
@RestController
//Class
public class EmailController {

	@Autowired private EmailService emailService;

	// Sending a simple Email
	@PostMapping("/sendMail")
	public String
	sendMail(@RequestBody EmailDetails details)
	{
		System.out.println("post");
		String status
			= emailService.sendSimpleMail(details);
		

		return status;
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}

	// Sending email with attachment
	@PostMapping("/sendMailWithAttachment")
	public String sendMailWithAttachment(
		@RequestBody EmailDetails details)
	{
		String status
			= emailService.sendMailWithAttachment(details);

		return status;
	}
}
