package com.example.inventoryservice.dto;

import java.io.Serializable;

public class InventoryReponse implements Serializable{
		
	    private String idProduct;
	    
	    //sl hien con trong kho
	    private Integer quatity;
	    
	    //sl sp da ban
	    private Integer numberOfProductSoild;

	
		

		public InventoryReponse(String idProduct, Integer quatity, Integer numberOfProductSoild) {
			super();
			this.idProduct = idProduct;
			this.quatity = quatity;
			this.numberOfProductSoild = numberOfProductSoild;
		}


	
		@Override
		public String toString() {
			return "InventoryReponse [idProduct=" + idProduct + ", quatity=" + quatity + ", numberOfProductSoild="
					+ numberOfProductSoild + "]";
		}



		public InventoryReponse() {
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
