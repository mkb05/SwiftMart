package com.order_service.order.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order_service.order.Entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long>{

}
