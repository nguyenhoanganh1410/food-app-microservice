package com.example.orderservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.orderservice.dto.OrderLineDto;
import com.example.orderservice.dto.OrderReponse;
import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderItem;
import com.example.orderservice.repository.OrderRepository;

@Service
@Transactional
public class OrderService {
	private OrderRepository orderRepository;
	
	
	


	public OrderService(OrderRepository orderRepository) {
		super();
		this.orderRepository = orderRepository;
	}

	public Order createOrder(OrderRequest orderRequest) {
			Order order = new Order();
						
			List<OrderItem> list = orderRequest.getOrderLineDtos().stream().map(this::mapToDto).toList();
			//List<OrderItem> list = (List<OrderItem>) orderRequest.getOrderLineDtos().stream().map(this::mapToDto);
			
			order.setCustomerAddress(orderRequest.getCustomerAddress());
			order.setCustomerEmail(orderRequest.getCustomerEmail());
			order.setOrderItems(list);
			//save into db
			orderRepository.save(order);
			
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
