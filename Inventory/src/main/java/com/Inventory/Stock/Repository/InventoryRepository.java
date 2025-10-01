package com.Inventory.Stock.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Inventory.Stock.Entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {

	Optional<Inventory> findByProductId(Long productId);
}
