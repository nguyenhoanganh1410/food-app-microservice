package com.example.inventoryservice.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.inventoryservice.entity.Inventory;


@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>{
	 Inventory findByIdProduct(String id);
	 List<Inventory> findByIdProductIn(List<String> idProducts);
	 @Modifying
	 @Query(value = "update t_inventory u set u.quatity = ?, u.number_of_product_soild = ? where u.id_product = ?", 
	  nativeQuery = true)
	int updateInventorySetQuatityForIdProductNative(Integer quantity,Integer numberOfProductSoild, String idProduct);
}
