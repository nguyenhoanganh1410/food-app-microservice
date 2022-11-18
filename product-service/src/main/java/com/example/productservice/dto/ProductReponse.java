package com.example.productservice.dto;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;



public class ProductReponse implements Serializable{
	
	private String id;
	private String name;
	private String desc;
	private BigDecimal price;
	private String category;
	//private int stock;
	private ArrayList<String> images;
	
	
	public ProductReponse(String id, String name, String desc, BigDecimal price, String category, 
			ArrayList<String> images) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.price = price;
		this.category = category;
		
		this.images = images;
	}
	public ProductReponse() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
	public ArrayList<String> getImages() {
		return images;
	}
	public void setImages(ArrayList<String> images) {
		this.images = images;
	}
	
	
	
	
}
