package com.example.orderservice.dto;



public class InventoryRequest {
	private String idProduct;
    
    //sl hien con trong kho
    private Integer quatity;

	public InventoryRequest(String idProduct, Integer quatity) {
		super();
		this.idProduct = idProduct;
		this.quatity = quatity;
	}

	public InventoryRequest() {
		super();
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
    
    
 
}