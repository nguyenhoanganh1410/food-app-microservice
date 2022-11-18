package com.example.inventoryservice.dto;



public class InventoryRequest {

  
    @Override
	public String toString() {
		return "InventoryRequest [idProduct=" + idProduct + ", quatity=" + quatity + ", numberOfProductSoild="
				+ numberOfProductSoild + "]";
	}

	private String idProduct;
    
    //sl hien con trong kho
    private Integer quatity;
    
    //sl sp da ban
    private Integer numberOfProductSoild;

	public InventoryRequest(String idProduct, Integer quatity, Integer numberOfProductSoild) {
		super();
		this.idProduct = idProduct;
		this.quatity = quatity;
		this.numberOfProductSoild = numberOfProductSoild;
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

	public Integer getNumberOfProductSoild() {
		return numberOfProductSoild;
	}

	public void setNumberOfProductSoild(Integer numberOfProductSoild) {
		this.numberOfProductSoild = numberOfProductSoild;
	}
    
    
    
}