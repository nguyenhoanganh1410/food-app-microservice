package com.example.RedisDemo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Product implements Serializable {
	
	private String id;
	private String name;
	private String desc;
	private double price;
	private String category;
	private boolean stock = true;
	private ArrayList<String> images;



	public Product(String name, String desc, double price, String category, ArrayList<String> images) {
		this.id =String.valueOf(new Date().getTime());
		this.name = name;
		this.desc = desc;
		this.price = price;
		this.category = category;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isStock() {
		return stock;
	}

	public void setStock(boolean stock) {
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
		return "Product{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", desc='" + desc + '\'' +
				", price=" + price +
				", category='" + category + '\'' +
				", stock=" + stock +
				", images=" + images +
				'}';
	}
}