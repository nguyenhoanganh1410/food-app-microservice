
package com.example.productservice.dto;


import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;



public class ProductRequest {
	
	private String name;
	private String desc;
	private BigDecimal price;
	private String category;
	private ArrayList<String> images;
	
	public ProductRequest() {
		super();
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
