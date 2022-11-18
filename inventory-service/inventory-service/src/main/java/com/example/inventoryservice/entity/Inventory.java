package com.example.inventoryservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_inventory")

public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idProduct;
    
    //sl hien con trong kho
    private Integer quatity;
    
    //sl sp da ban
    private Integer numberOfProductSoild;

	public Inventory(String idProduct, Integer quatity, Integer numberOfProductSoild) {
		super();
		this.idProduct = idProduct;
		this.quatity = quatity;
		this.numberOfProductSoild = numberOfProductSoild;
	}

	public Inventory() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(String idProduct) {
		this.idProduct = idProduct;
	}



	public Integer getQuatity() {
		return quatity;
	}

	public void setQuatity(Integer quatity) {
		this.quatity = quatity;
	}

	public Integer getNumberOfProductSoild() {
		return numberOfProductSoild;
	}

	public void setNumberOfProductSoild(Integer numberOfProductSoild) {
		this.numberOfProductSoild = numberOfProductSoild;
	}

	@Override
	public String toString() {
		return "Inventory [id=" + id + ", idProduct=" + idProduct + ", quatity=" + quatity + ", numberOfProductSoild="
				+ numberOfProductSoild + "]";
	}

	
    
    
}