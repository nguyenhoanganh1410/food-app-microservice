package com.example.orderservice.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String customerEmail;
	
	private String customerAddress;
//	//1: dat hang
//	//0: huy
//	//2: da giao
	@Column(name = "status_order", nullable = false)
	private int statusOrder = 1;
	@Column(name = "delete_status", nullable = false)
	private int deleteStatus = 0;
	

	@OneToMany(cascade = CascadeType.ALL)
	private List<OrderItem> orderItems;


	public Order() {
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
