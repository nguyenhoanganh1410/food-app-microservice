package com.example.productservice.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Product implements Serializable{
	@Id
	private String id;
	private String name;
	private String desc;
	private BigDecimal price;
	private String category;
	private int stock;
	private ArrayList<String> images;
	public Product() {
		super();
	}
	public Product(String id, String name, String desc, BigDecimal price, String category, int stock,
			ArrayList<String> images) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.price = price;
		this.category = category;
		this.stock = stock;
		this.images = images;
	}
	public Product(String name, String desc, BigDecimal price, String category, int stock, ArrayList<String> images) {
		super();
		this.name = name;
		this.desc = desc;
		this.price = price;
		this.category = category;
		this.stock = stock;
		this.images = images;
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
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public ArrayList<String> getImages() {
		return images;
	}
	public void setImages(ArrayList<String> images) {
		this.images = images;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", desc=" + desc + ", price=" + price + ", category=" + category
				+ ", stock=" + stock + ", images=" + images + "]";
	}
	
	
	
	
	
	
}
