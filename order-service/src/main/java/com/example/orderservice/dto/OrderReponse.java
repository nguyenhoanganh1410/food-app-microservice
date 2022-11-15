package com.example.orderservice.dto;

import java.util.List;

import com.example.orderservice.entity.OrderItem;

public class OrderReponse {

	private Long id;
	
	private String customerEmail;
	
	private String customerAddress;

	private int statusOrder = 1;

	private int deleteStatus = 0;
	
	private List<OrderItem> orderItems;
	
	public OrderReponse() {
		super();
		
	}

	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}



	public int getStatusOrder() {
		return statusOrder;
	}



	public void setStatusOrder(int statusOrder) {
		this.statusOrder = statusOrder;
	}



	public int getDeleteStatus() {
		return deleteStatus;
	}



	public void setDeleteStatus(int deleteStatus) {
		this.deleteStatus = deleteStatus;
	}



	public List<OrderItem> getOrderItems() {
		return orderItems;
	}



	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}


}
