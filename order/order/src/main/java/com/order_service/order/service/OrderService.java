package com.order_service.order.service;

import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.order_service.order.Entity.Order;
import com.order_service.order.Entity.OrderItem;
import com.order_service.order.Repository.OrderRepository;

@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final KafkaTemplate<String,Order> kafkaTemplate;

	public OrderService(OrderRepository orderRepository,KafkaTemplate<String,Order> kafkaTemplate) {
		super();
		this.orderRepository = orderRepository;
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public Order createOrder(Order order) {
        order.setStatus("PENDING");

        
        double total = 0;
        for (OrderItem item : order.getItems()) {
            total += item.getPrice() * item.getQuantity();
            item.setOrder(order); 
        }
        order.setTotalPrice(total);

        Order savedOrder= orderRepository.save(order);
        
        //Produce event
        Order event=new Order(
        		savedOrder.getId(),
        		savedOrder.getUserId(),
        		savedOrder.getStatus(),
        		savedOrder.getTotalPrice(),
        		savedOrder.getItems().stream()
        					.map(i -> new OrderItem(i.getProductId(),i.getQuantity()))
        					.toList()
        		);
        	kafkaTemplate.send("order-events",event);
        	
        	return savedOrder;
    }
	
	@KafkaListener(topics="inventory-events",groupId="order-service")
	public void handleInventoryUpdate(Order event) {
		Order order=orderRepository.findById(event.getId())
				.orElseThrow(() -> new RuntimeException("Order not found"));
		
		if("INVENTORY_CONFIRMED".equals(event.getStatus())) {
			order.setStatus("CONFIRMED");
		}else if("INVENTORY_FAILED".equals(event.getStatus())){
			order.setStatus("CANCELED");
		}
		
		orderRepository.save(order);
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
