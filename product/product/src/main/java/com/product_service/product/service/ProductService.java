package com.product_service.product.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product_service.product.Entity.Product;
import com.product_service.product.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;
	private ProductPopularityService productPopularityService;

	public ProductService(ProductRepository productRepository,ProductPopularityService productPopularityService) {
		super();
		this.productRepository = productRepository;
		this.productPopularityService=productPopularityService;
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
		
		
		Product product= productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found"));
		
		productPopularityService.recordView(id.toString()); //Redis Update
		
		return product;
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
