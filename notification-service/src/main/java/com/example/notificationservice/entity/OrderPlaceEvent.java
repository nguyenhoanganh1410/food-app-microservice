package com.example.notificationservice.entity;


import java.math.BigDecimal;
import java.util.Date;

public class OrderPlaceEvent {
	 private Long orderNumber;
	 private String cusomterEmail;
	 private String cusomterAddress;
	 private Date createAt = new Date();
	
	 


		

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public OrderPlaceEvent() {
		super();
	}

	public String getCusomterAddress() {
		return cusomterAddress;
	}

	public void setCusomterAddress(String cusomterAddress) {
		this.cusomterAddress = cusomterAddress;
	}

	public OrderPlaceEvent(Long long1) {
		super();
		this.orderNumber = long1;
	}

	public String getCusomterEmail() {
		return cusomterEmail;
	}

	public void setCusomterEmail(String cusomterEmail) {
		this.cusomterEmail = cusomterEmail;
	}

	public OrderPlaceEvent(Long orderNumber, String cusomterEmail) {
		super();
		this.orderNumber = orderNumber;
		this.cusomterEmail = cusomterEmail;
	}

	public Long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}
	 
	 
}
