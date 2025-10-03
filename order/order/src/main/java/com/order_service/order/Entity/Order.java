package com.order_service.order.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;   

    private String status; 

    private Double totalPrice;
    
    @OneToMany(mappedBy="order", cascade=CascadeType.ALL, orphanRemoval=true)
    @JsonManagedReference
    private List<OrderItem> items;
    
    
    
    
    
    

	public Order() {
		super();
	}

	public Order(Long id, Long userId, String status, Double totalPrice, List<OrderItem> items) {
		super();
		this.id = id;
		this.userId = userId;
		this.status = status;
		this.totalPrice = totalPrice;
		this.items = items;
	}

	
	@Override
	public String toString() {
	    return "Order{id=" + id +
	           ", userId=" + userId +
	           ", status='" + status + '\'' +
	           ", totalPrice=" + totalPrice +
	           ", items=" + items + "}";
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

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
    
    
}
