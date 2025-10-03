package com.order_service.order.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="orders_items")
public class OrderItem {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private Long productId; 

	    private Integer quantity;

	    private Double price;
	    
	    @ManyToOne
	    @JoinColumn(name="order_id")
	    @JsonBackReference
	    private Order order;
	    
	    

		public OrderItem() {
			super();
		}

		public OrderItem(Long productId, Integer quantity) {
			super();
			this.productId = productId;
			this.quantity = quantity;
		}
		
		
		@Override
		public String toString() {
		    return "OrderItem{id=" + id +
		           ", productId=" + productId +
		           ", quantity=" + quantity +
		           ", price=" + price + "}";
		}


		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
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

		public Double getPrice() {
			return price;
		}

		public void setPrice(Double price) {
			this.price = price;
		}

		public Order getOrder() {
			return order;
		}

		public void setOrder(Order order) {
			this.order = order;
		}
	    
	    
}
