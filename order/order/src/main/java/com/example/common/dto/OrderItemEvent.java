package com.example.common.dto;

public class OrderItemEvent {


    private Long productId; 

    private Integer quantity;
    
    

	public OrderItemEvent() {
		super();
	}

	public OrderItemEvent(Long productId, Integer quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
