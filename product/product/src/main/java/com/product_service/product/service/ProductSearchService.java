package com.product_service.product.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.common.dto.ProductCreatedEvent;
import com.product_service.product.repository.ProductSearchRepository;
import com.product_service.product.search.Document.ProductSearchDocument;

@Service
public class ProductSearchService {

	private final ProductSearchRepository repo;
	

	public ProductSearchService(ProductSearchRepository repo) {
		super();
		this.repo = repo;
	}
	
	@KafkaListener(topics="product-created",groupId="product-service")
	public void indexProduct(ProductCreatedEvent  product) {
		ProductSearchDocument doc=mapDocument(product);
		repo.save(doc);
	}
	
	//Converting product to productdoc to store in elasticrepo 
	private ProductSearchDocument mapDocument(ProductCreatedEvent  product) {
		ProductSearchDocument doc=new ProductSearchDocument();
		doc.setId(product.getId());
		doc.setName(product.getName());
		doc.setDescription(product.getDescription());
		doc.setCategory(product.getCategory());
		doc.setPrice(product.getPrice());
		
		return doc;
	}
}
