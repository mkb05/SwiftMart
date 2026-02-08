package com.order_service.order.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.dto.AddToCartRequest;
import com.example.common.dto.UpdateCartItemRequest;
import com.order_service.order.Entity.Cart;
import com.order_service.order.service.CartService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/order-cart/cart")
public class CartController {

	private final CartService service;

	public CartController(CartService service) {
		super();
		this.service = service;
	}
	
	
	@PostMapping("/add")
    public ResponseEntity<Cart> addToCart(
            HttpServletRequest request,
            @RequestBody AddToCartRequest req) {

        Long userId = (Long) request.getAttribute("userId");
        if (userId == null) return ResponseEntity.status(401).build();

        return ResponseEntity.ok(
            service.addToCart(userId, req.getProductId(), req.getQuantity(), req.getPrice())
        );
    }
	
	@GetMapping
    public ResponseEntity<Cart> getCart(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId == null) return ResponseEntity.status(401).build();

        return ResponseEntity.ok(service.getCart(userId));
    }
	
	@PutMapping("/update")
    public ResponseEntity<Cart> updateItem(
            HttpServletRequest request,
            @RequestBody UpdateCartItemRequest req) {

        Long userId = (Long) request.getAttribute("userId");
        if (userId == null) return ResponseEntity.status(401).build();

        return ResponseEntity.ok(
            service.updateQuantity(userId, req.getProductId(), req.getQuantity())
        );
}
}
