package com.example.notificationservice;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;

import com.example.notificationservice.entity.EmailDetails;
import com.example.notificationservice.entity.OrderPlaceEvent;
import com.example.notificationservice.service.EmailService;

@SpringBootApplication
@EnableEurekaClient
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}
	
	
	@Autowired private EmailService emailService;
	
	@KafkaListener(topics = "notificationTopic")
    public void handleNotification(OrderPlaceEvent orderPlacedEvent) {
        // send out an email notification
       System.out.println(("Received Notification for Order - {}"+orderPlacedEvent.getCusomterEmail() ));
       System.out.println("order mess" + orderPlacedEvent);
//       EmailDetails emailDetails = new EmailDetails(orderPlacedEvent.getCusomterEmail(), "Đơn hàng của bạn đã đặt thành công với mã là : " + orderPlacedEvent.getOrderNumber(), "FoodApp - Đặt hàng thành công");
       EmailDetails emailDetails = new EmailDetails(orderPlacedEvent.getCusomterEmail(), "[Food App]\n"
       		+ "	\n"
       		+ "Woo hoo! Your order is on its way. Your order details can be found below.\n"
       		+ "	\n"

       		+ "\n"
       		+ "Order #: ["+orderPlacedEvent.getOrderNumber()+"]\n"
       		+ "Order Date: ["+orderPlacedEvent.getCreateAt()+"]\n"
       		+ "SHIPPING ADDRESS: ["+orderPlacedEvent.getCusomterAddress()+"]\n"
       		+ "\n"
       		+ "Thank you for placing your order!\n"
       		
       		+ " : " , "FoodApp - Đặt hàng thành công");
       String status = emailService.sendSimpleMail(emailDetails);
       System.out.println("send email status : " + status);


		
       

	}

}
