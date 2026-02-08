package com.order_service.order.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order_service.order.Entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	 Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);
}
