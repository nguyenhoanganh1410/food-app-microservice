package com.example.orderservice.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.orderservice.controller.OrderNotFoundException;
import com.example.orderservice.dto.InventoryReponse;
import com.example.orderservice.dto.InventoryRequest;
import com.example.orderservice.dto.OrderLineDto;
import com.example.orderservice.dto.OrderReponse;
import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderItem;
import com.example.orderservice.event.OrderPlaceEvent;
import com.example.orderservice.repository.OrderRepository;

@Service
@Transactional
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private  KafkaTemplate<String, OrderPlaceEvent> kafkaTemplate;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private int count = 0;
		
	public OrderService() {
		super();
	}

	public OrderService(OrderRepository orderRepository, KafkaTemplate<String, OrderPlaceEvent> kafkaTemplate) {
			super();
			this.orderRepository = orderRepository;
			this.kafkaTemplate = kafkaTemplate;
		}


	public Order createOrder(OrderRequest orderRequest) {
		
		 System.out.println(" Making a request " + ++count + " at :" + LocalDateTime.now());
			Order order = new Order();
						
			List<OrderItem> list = orderRequest.getOrderLineDtos().stream().map(this::mapToDto).toList();
			//List<OrderItem> list = (List<OrderItem>) orderRequest.getOrderLineDtos().stream().map(this::mapToDto);
			
			order.setCustomerAddress(orderRequest.getCustomerAddress());
			order.setCustomerEmail(orderRequest.getCustomerEmail());
			order.setOrderItems(list);
			
			//check inventory in here
			//call inventory service -> get quantyti product
			list.forEach(val ->{
				
				InventoryReponse d =  restTemplate.getForObject("http://INVENTORY-SERVICE/v1/api/inventory?idProduct=" + val.getProductId(), InventoryReponse.class);
				System.out.println("inventory "+ d.toString());
				//check isInStock or not
				if(d.getQuatity() < val.getQuatity()) {
					System.out.println("Khong du hang roi");
					//throw new IllegalArgumentException("Product is not in stock, please try again later");
					throw new OrderNotFoundException("Product is not in stock, please try again later ");
				}
			});
			
			//save into db
			Order orderSave = orderRepository.save(order);
			
			//call api for update quatity in Inventory Service
			// create headers
			list.forEach(val ->{
				    Map < String, String > params = new HashMap < String, String > ();
			        params.put("idProduct", val.getProductId());
			        InventoryRequest i = new InventoryRequest(val.getProductId(),val.getQuatity());
			      
			        restTemplate.put("http://INVENTORY-SERVICE/v1/api/inventory?idProduct="+val.getProductId(), i);
			});
			
			
//			//kafka
//			kafkaTemplate.send("notificationTopic", new OrderPlaceEvent(orderSave.getId(), order.getCustomerEmail(), order.getCustomerAddress()));
//			 
//			System.out.println("send event to notofication service");
//			
			return order;
		}
	
	
		private OrderItem mapToDto(OrderLineDto item) {
			// TODO Auto-generated method stub
			OrderItem o = new OrderItem(item.getProductName(),item.getProductId(), item.getPrice(), item.getQuatity());
			return o;
		}
		
		
		public ResponseEntity<Map<String, Object>> getOrdersByEmail(int page,int size,String customerEmail) {
			  try {
			      List<Order> tutorials = new ArrayList<Order>();
			      Pageable paging = PageRequest.of(page, size);
			      System.out.println("customerEmail" + customerEmail);
			     
			      Page<Order> pageTuts = null;
			     
			    pageTuts = orderRepository.findByCustomerEmail(customerEmail, paging);

			      tutorials = pageTuts.getContent();
			     
			      Map<String, Object> response = new HashMap<>();
			      response.put("orders", tutorials);
			      response.put("currentPage", pageTuts.getNumber());
			      response.put("totalItems", pageTuts.getTotalElements());
			      response.put("totalPages", pageTuts.getTotalPages());

			      return new ResponseEntity<>(response, HttpStatus.OK);
			    } catch (Exception e) {
			      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			    }
		}

		public OrderReponse getOrderByID(Long id) {
			// TODO Auto-generated method stub
			Optional<Order> order = orderRepository.findById(id);
			if(order.isEmpty()) {
				return null;
			}
			Order o = order.get();
			System.out.println("Order" + o);
			OrderReponse orderReponse = new OrderReponse();
			orderReponse.setId(o.getId());
			orderReponse.setCustomerEmail(o.getCustomerEmail());
			orderReponse.setCustomerAddress(o.getCustomerAddress());
			orderReponse.setDeleteStatus(o.getDeleteStatus());
			orderReponse.setStatusOrder(o.getStatusOrder());
			orderReponse.setOrderItems(o.getOrderItems());
			
			
			return orderReponse;
		}

		
}
