package com.order_service.order.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order_service.order.Entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {

	List<Order> findByUserId(Long userId);
}
