package com.order_service.order.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.order_service.order.Entity.Order;
import com.order_service.order.Entity.OrderItem;
import com.order_service.order.Repository.OrderRepository;

@Service
public class OrderService {

	private final OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository) {
		super();
		this.orderRepository = orderRepository;
	}
	
	public Order createOrder(Order order) {
        order.setStatus("PENDING");

        
        double total = 0;
        for (OrderItem item : order.getItems()) {
            total += item.getPrice() * item.getQuantity();
            item.setOrder(order); 
        }
        order.setTotalPrice(total);

        return orderRepository.save(order);
    }
	
	 public Order getOrder(Long id) {
	        return orderRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Order not found"));
	    }

	    public List<Order> getOrdersByUser(Long userId) {
	        return orderRepository.findByUserId(userId);
	    }
	    
	    public List<Order> getAllOrders() {
	        return orderRepository.findAll();
	    }

	    public Order cancelOrder(Long id) {
	        Order order = getOrder(id);
	        order.setStatus("CANCELLED");
	        return orderRepository.save(order);
	    }
}
