package com.example.orderservice.dto;

import java.math.BigDecimal;

public class OrderLineDto {
	private String productName;
	
	private String productId;
	
	private BigDecimal price;
	
	
	private Integer quatity;


	public OrderLineDto(String productName, String productId, BigDecimal price, Integer quatity) {
		super();
		this.productName = productName;
		this.productId = productId;
		this.price = price;
		this.quatity = quatity;
	}


	public OrderLineDto() {
		super();
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public Integer getQuatity() {
		return quatity;
	}


	public void setQuatity(Integer quatity) {
		this.quatity = quatity;
	}
	
	
}
