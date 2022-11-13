//package com.example.notificationservice.controller;
////Java Program to Create Rest Controller that
////Defines various API for Sending Mail
//
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.notificationservice.entity.EmailDetails;
//import com.example.notificationservice.entity.OrderPlaceEvent;
//import com.example.notificationservice.service.EmailService;
//
////Annotation
////@RestController
////Class
//public class EmailController {
//
//	@Autowired private EmailService emailService;
//	
//	@KafkaListener(topics = "notificationTopic")
//    public void handleNotification(OrderPlaceEvent orderPlacedEvent) {
//        // send out an email notification
//       System.out.println(("Received Notification for Order - {}"+orderPlacedEvent.getCusomterEmail() ));
//       EmailDetails emailDetails = new EmailDetails(orderPlacedEvent.getCusomterEmail(), "The order id : " + orderPlacedEvent.getOrderNumber(), "Placed An Order Successfully");
//       String status = emailService.sendSimpleMail(emailDetails);
//       System.out.println("send email status : " + status);
//
//
//	}
////
////	// Sending a simple Email
////	@PostMapping("/")
////	public String
////	sendMail(@RequestBody EmailDetails details)
////	{
////		System.out.println("post");
////		String status
////			= emailService.sendSimpleMail(details);
////		
////
////		return status;
////	}
////	
////	@GetMapping("/hello")
////	public String hello() {
////		return "hello";
////	}
////
////	// Sending email with attachment
////	@PostMapping("/sendMailWithAttachment")
////	public String sendMailWithAttachment(
////		@RequestBody EmailDetails details)
////	{
////		String status
////			= emailService.sendMailWithAttachment(details);
////
////		return status;
////	}
//}
