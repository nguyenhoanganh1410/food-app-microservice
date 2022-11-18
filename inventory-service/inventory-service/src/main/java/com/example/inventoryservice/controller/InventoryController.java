package com.example.inventoryservice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.inventoryservice.dto.InventoryReponse;
import com.example.inventoryservice.dto.InventoryRequest;
import com.example.inventoryservice.entity.Inventory;
import com.example.inventoryservice.service.InventoryService;


@RestController
@RequestMapping("/v1/api/inventory")
public class InventoryController {
	@Autowired
	private InventoryService inventoryService;
	
	@PostMapping
	public ResponseEntity create(@RequestBody InventoryRequest inventoryRequest) {
		inventoryService.creatInventory(inventoryRequest);
		return new ResponseEntity<>(inventoryRequest, HttpStatus.CREATED);
	}
	
	///v1/api/inventory?idProduct=abcxyz&idProduct=mkfmvkfm
//	@GetMapping()
//	@ResponseStatus(HttpStatus.OK)
//	public List<InventoryReponse> getInventoryByIdProduct(@RequestParam  List<String> idProduct) {
//		return inventoryService.isInStock(idProduct);
//	}
	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public InventoryReponse getInventoryByIdProduct(@RequestParam String idProduct) {
		return inventoryService.getProductById(idProduct);
	}
	
	  @GetMapping("/list")
	  public ResponseEntity<Map<String, Object>> getListOfInventity(
	      @RequestParam(defaultValue = "0") int page,
	      @RequestParam(defaultValue = "20") int size){
		 
		 return inventoryService.getAllInventity(page, size);
	  }
	  
	  
	  //v1/api/inventory?idProduct=xxxxx
	  @PutMapping()
	  public ResponseEntity<InventoryReponse> updateInventory(@RequestParam String idProduct, @RequestBody InventoryRequest inventoryRequest) {
	    InventoryReponse inventionData = inventoryService.getProductById(idProduct);
	    System.out.println("inventionData1" + inventionData);
	    if (inventionData == null) {
			throw new InventoryNotFoundException("Product id not found: "
					+ " " + idProduct);
		}
   
	    //set data
	    inventionData.setIdProduct(inventoryRequest.getIdProduct());
	    inventionData.setNumberOfProductSoild(inventionData.getNumberOfProductSoild() + inventoryRequest.getQuatity());
	    inventionData.setQuatity(inventionData.getQuatity() - inventoryRequest.getQuatity());
    
	    System.out.println("inventionData" + inventionData.toString());
	    
	    InventoryReponse p = inventoryService.updateInvention(inventionData, idProduct);
	    
	    
	    return  new ResponseEntity<>(inventionData, HttpStatus.OK);

	  }
	  
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
}
