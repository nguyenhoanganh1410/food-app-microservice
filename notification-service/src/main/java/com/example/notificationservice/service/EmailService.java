package com.example.notificationservice.service;

import com.example.notificationservice.entity.EmailDetails;

//Java Program to Illustrate Creation Of
//Service Interface


//Importing required classes


//Interface
public interface EmailService {

	// Method
	// To send a simple email
	String sendSimpleMail(EmailDetails details);

	// Method
	// To send an email with attachment
	String sendMailWithAttachment(EmailDetails details);
}
