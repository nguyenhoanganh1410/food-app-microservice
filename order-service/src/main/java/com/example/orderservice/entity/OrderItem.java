package com.example.orderservice.entity;

import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
@Entity
@Table(name = "order_item")
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String productName;
	
	private String productId;
	
	private BigDecimal price;
	
	
	private Integer quatity;


	public OrderItem() {
		super();
	}


	public OrderItem(String productName, String productId, BigDecimal price, Integer quatity) {
		super();
		this.productName = productName;
		this.productId = productId;
		this.price = price;
		this.quatity = quatity;
	}


	public OrderItem(Long id, String productName, String productId, BigDecimal price, Integer quatity) {
		super();
		this.id = id;
		this.productName = productName;
		this.productId = productId;
		this.price = price;
		this.quatity = quatity;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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
