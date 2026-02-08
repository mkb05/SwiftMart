package com.order_service.order.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order_service.order.Entity.Cart;
import com.order_service.order.Entity.Cart.CartStatus;

public interface CartRepository extends JpaRepository<Cart,Long> {

	Optional<Cart> findByUserIdAndStatus(Long userId, CartStatus status);

}
