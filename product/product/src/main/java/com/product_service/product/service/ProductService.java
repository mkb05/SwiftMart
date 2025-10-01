package com.product_service.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.product_service.product.Entity.Product;
import com.product_service.product.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}
	
	
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}
	
	public Product updateProduct(Long id,Product product) {
		Product existing=productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found"));
		
		existing.setName(product.getName());
		existing.setDescription(product.getDescription());
		existing.setPrice(product.getPrice());
		existing.setCategory(product.getCategory());
		
		return productRepository.save(existing);
	}
	
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
	
	public Product getProduct(Long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found"));
	}
	
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	public List<Product> searchByName(String name){
		return productRepository.findByNameContainingIgnoreCase(name);
	}
	
	public List<Product> searchByCategory(String category){
		return productRepository.findByCategoryIgnoreCase(category);
	}
}
