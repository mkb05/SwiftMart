package com.product_service.product.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product_service.product.Entity.Product;
import com.product_service.product.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}
		
	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		return ResponseEntity.ok(productService.createProduct(product));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id,@RequestBody Product product){
		return ResponseEntity.ok(productService.updateProduct(id, product));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
		productService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable Long id) {
	        return ResponseEntity.ok(productService.getProduct(id));
	}

	 @GetMapping
	 public ResponseEntity<List<Product>> getAllProducts() {
	        return ResponseEntity.ok(productService.getAllProducts());
	 }
	 
	 @GetMapping("/search")
	 public ResponseEntity<List<Product>> searchProducts(@RequestParam(required=false) String name, @RequestParam(required=false) String category){
		 
		 if(name!=null) {
			 return ResponseEntity.ok(productService.searchByName(name));
		 }else if(category!=null) {
			 return ResponseEntity.ok(productService.searchByCategory(category));
		 }
		 
		 return ResponseEntity.ok(productService.getAllProducts());
	 }
	
}
