package com.order_service.order.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.order_service.order.Entity.Order;
import com.order_service.order.service.OrderService;

@RestController
@RequestMapping("api/orders")
public class OrderController {

	private final OrderService service;

	public OrderController(OrderService service) {
		super();
		this.service = service;
	}
	
	 @PostMapping
	    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
	        return ResponseEntity.ok(service.createOrder(order));
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
	        return ResponseEntity.ok(service.getOrder(id));
	    }

	    @GetMapping
	    public ResponseEntity<List<Order>> getOrdersByUser(@RequestParam(required = false) Long userId) {
	        if (userId != null) {
	            return ResponseEntity.ok(service.getOrdersByUser(userId));
	        }
	        return ResponseEntity.ok(service.getAllOrders());
	    }

	    @PutMapping("/{id}/cancel")
	    public ResponseEntity<Order> cancelOrder(@PathVariable Long id) {
	        return ResponseEntity.ok(service.cancelOrder(id));
	    }
}
