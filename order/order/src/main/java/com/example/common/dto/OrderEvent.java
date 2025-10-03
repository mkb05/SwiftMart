package com.example.common.dto;

import java.util.List;



import jakarta.persistence.Id;

public class OrderEvent {

	@Id
    
    private Long id;

    private Long userId;   

    private String status; 

    private Double totalPrice;
    
    private List<OrderItemEvent> items;

    
	public OrderEvent() {
		super();
	}

	public OrderEvent(Long id, Long userId, String status, Double totalPrice, List<OrderItemEvent> list) {
		super();
		this.id = id;
		this.userId = userId;
		this.status = status;
		this.totalPrice = totalPrice;
		this.items = list;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<OrderItemEvent> getItems() {
		return items;
	}

	public void setItems(List<OrderItemEvent> items) {
		this.items = items;
	}
    
}
