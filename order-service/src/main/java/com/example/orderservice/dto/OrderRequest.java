package com.example.orderservice.dto;

import java.math.BigDecimal;
import java.util.List;

public class OrderRequest {
	private List<OrderLineDto> orderLineDtos;
	
	private String customerEmail;
	
	private String customerAddress;

	public OrderRequest(List<OrderLineDto> orderLineDtos) {
		super();
		this.orderLineDtos = orderLineDtos;
	}
	

	public OrderRequest() {
		super();
	}


	public List<OrderLineDto> getOrderLineDtos() {
		return orderLineDtos;
	}

	public void setOrderLineDtos(List<OrderLineDto> orderLineDtos) {
		this.orderLineDtos = orderLineDtos;
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
	
	

}
