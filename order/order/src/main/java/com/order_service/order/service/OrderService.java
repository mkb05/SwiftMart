package com.order_service.order.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.common.dto.OrderEvent;
import com.example.common.dto.OrderItemEvent;
import com.example.common.dto.PaymentEvent;
import com.order_service.order.Entity.Cart;
import com.order_service.order.Entity.Cart.CartStatus;
import com.order_service.order.Entity.Order;
import com.order_service.order.Entity.OrderItem;
import com.order_service.order.Repository.CartRepository;
import com.order_service.order.Repository.OrderRepository;

@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final KafkaTemplate<String,OrderEvent> kafkaTemplate;
	private final CartRepository cartRepo;

	public OrderService(OrderRepository orderRepository,KafkaTemplate<String, OrderEvent> kafkaTemplate,CartRepository cartRepo) {
		super();
		this.orderRepository = orderRepository;
		this.kafkaTemplate = kafkaTemplate;
		this.cartRepo=cartRepo;
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
        OrderEvent event=new OrderEvent(
        		savedOrder.getId(),
        		savedOrder.getUserId(),
        		savedOrder.getStatus(),
        		savedOrder.getTotalPrice(),
        		savedOrder.getItems().stream()
        					.map(i -> new OrderItemEvent(i.getProductId(),i.getQuantity()))
        					.toList()
        		);
        	kafkaTemplate.send("order-events-inventory",event);
        	
        	
        	
        	return savedOrder;
    }
	
	public Order createOrderFromCart(Long userId) {

	    Cart cart = cartRepo.findByUserIdAndStatus(userId, CartStatus.ACTIVE)
	            .orElseThrow(() -> new RuntimeException("Cart not found"));

	    Order order = new Order();
	    order.setUserId(userId);

	    order.setItems(
	        cart.getItems().stream().map(item -> {
	            OrderItem oi = new OrderItem();
	            oi.setProductId(item.getProductId());
	            oi.setQuantity(item.getQuantity());
	            oi.setPrice(item.getPrice());
	            return oi;
	        }).collect(Collectors.toList())
	    );

	    cart.setStatus(CartStatus.CHECKED_OUT);
	    cartRepo.save(cart);

	    return orderRepository.save(order);
	}

	
	@Transactional
	@KafkaListener(topics="inventory-events",groupId="order-service")
	public void handleInventoryUpdate(OrderEvent event) {
		System.out.print("Kafka event response"+event);
		Order order=orderRepository.findById(event.getId())
				.orElseThrow(() -> new RuntimeException("Order not found"));
		
		if("INVENTORY_CONFIRMED".equals(event.getStatus())) {
			
			
			 OrderEvent Confirmedevent=new OrderEvent(
					 event.getId(),
					 event.getUserId(),
					 event.getStatus(),
					 event.getTotalPrice(),
					 event.getItems().stream()
		        					.map(i -> new OrderItemEvent(i.getProductId(),i.getQuantity()))
		        					.toList()
		        		);
			 
			 kafkaTemplate.send("order-events-payment",Confirmedevent);
			
		}else if("INVENTORY_FAILED".equals(event.getStatus())){
			order.setStatus("CANCELED");
		}
		
		orderRepository.save(order);
	}
	
	@Transactional
	@KafkaListener(topics="payment-events",groupId="order-service")
	public void handlePaymentUpdate(PaymentEvent event) {
		System.out.print("Kakfa payment response "+event);
		Order order=orderRepository.findById(event.getOrderId())
				.orElseThrow(() -> new RuntimeException("Order not found"));
		
		if("SUCCESS".equals(event.getPaymentStatus())) {
			order.setStatus("ORDER PLACED");
		}else if("FAILED".equals(event.getPaymentStatus())) {
			order.setStatus("PAYMENT FAILED");
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
