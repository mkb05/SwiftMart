package com.product_service.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product_service.product.Entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {

	List<Product> findByNameContainingIgnoreCase(String name);
	List<Product> findByCategoryIgnoreCase(String category);
}
