package com.example.inventoryservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.inventoryservice.dto.InventoryReponse;
import com.example.inventoryservice.dto.InventoryRequest;
import com.example.inventoryservice.entity.Inventory;
import com.example.inventoryservice.repository.InventoryRepository;


@Service
@Transactional
public class InventoryService {
	@Autowired
	private InventoryRepository inventoryRepository;
	
	  
	@Autowired
    private RedisTemplate<String, InventoryReponse> template;
	
//	 private HashOperations<String, Integer, InventoryReponse> hashOperations;
//	 private final String INVENTORY_CACHE = "INVENTORY";
//	 
//	 // This annotation makes sure that the method needs to be executed after
//	    // dependency injection is done to perform any initialization.
//	    @PostConstruct
//	    private void intializeHashOperations() {
//	        hashOperations = template.opsForHash();
//	    }

	public void creatInventory(InventoryRequest inventoryRequest) {
		// TODO Auto-generated method stub
		Inventory i = new Inventory(inventoryRequest.getIdProduct(), inventoryRequest.getQuatity(), inventoryRequest.getNumberOfProductSoild());
		inventoryRepository.save(i);
	}

	
	public InventoryReponse getProductById(String id) {
		// TODO Auto-generated method stub
		 var key = "inv_" + id;
		 ValueOperations<String, InventoryReponse>  operations = template.opsForValue();
	     final boolean hasKey = template.hasKey(key);
	     //check trong redis
	     if (hasKey) {
	            final InventoryReponse inventoryReponse =  (InventoryReponse) operations.get(key);
	            System.out.println("InventoryResponse : cache post >> " + inventoryReponse.toString());
	            return inventoryReponse;
	      }
		 
		Inventory i =  inventoryRepository.findByIdProduct(id);
		if(i==null) {
			return null;
		}
		InventoryReponse inventoryReponse = new InventoryReponse( i.getIdProduct(), i.getQuatity(), i.getNumberOfProductSoild());
		//System.out.println(i.toString());
		 operations.set(key, inventoryReponse);
         System.out.println("InventoryResponse : cache insert >> " + inventoryReponse.toString());
		
		return inventoryReponse;
	}


	public List<InventoryReponse> isInStock(List<String> idProducts) {
		// TODO Auto-generated method stub
		List<Inventory> list = inventoryRepository.findByIdProductIn(idProducts);
		if(list.size() == 0) {
			return null;
		}
		
		
		List<InventoryReponse> listInventoryReponses = new ArrayList<InventoryReponse>();
		list.forEach(val -> {
			System.out.println(val.toString());
			InventoryReponse i = new InventoryReponse(val.getIdProduct(), val.getQuatity(), val.getNumberOfProductSoild());
			listInventoryReponses.add(i);
		});
		return listInventoryReponses;
	}


	public ResponseEntity<Map<String, Object>> getAllInventity(int page, int size) {
		 try {
		      List<Inventory> tutorials = new ArrayList<Inventory>();
		      Pageable paging = PageRequest.of(page, size);
		    
		      Page<Inventory> pageTuts = null;
		     
		        pageTuts = inventoryRepository.findAll(paging);  
		     
		      tutorials = pageTuts.getContent();
		     
		      Map<String, Object> response = new HashMap<>();
		      response.put("inventities", tutorials);
		      response.put("currentPage", pageTuts.getNumber());
		      response.put("totalItems", pageTuts.getTotalElements());
		      response.put("totalPages", pageTuts.getTotalPages());

		      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		     
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}

	
	public InventoryReponse updateInvention(InventoryReponse inventionData, String idProduct) {
		  final String key = "inv_" + idProduct;
	        final boolean hasKey = template.hasKey(key);
	        if (hasKey) {
	        	template.delete(key);
	            System.out.println("InventoryResponse : cache delete >> " + idProduct);
	        }
		// TODO Auto-generated method stub
		int i = inventoryRepository.updateInventorySetQuatityForIdProductNative(inventionData.getQuatity(),inventionData.getNumberOfProductSoild(), idProduct);
		System.out.println(i);
		return null;
	}





	
}
