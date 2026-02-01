package com.product_service.product.service;

import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.product_service.product.Entity.Product;
import com.product_service.product.repository.ProductSearchRepository;
import com.product_service.product.search.Doc.Document.ProductSearchDocument;

@Service
public class ProductSearchService {

	private final ProductSearchRepository repo;
	

	public ProductSearchService(ProductSearchRepository repo) {
		super();
		this.repo = repo;
	}
	
	@KafkaListener(topics="product-created",groupId="product-service")
	public void indexProduct(Product  product) {
		ProductSearchDocument doc=mapDocument(product);
		 System.out.println("INDEXING PRODUCT: " + doc.getName());
		repo.save(doc);
	}
	
	public List<ProductSearchDocument> searchByName(String name){
		List<ProductSearchDocument> searchDoc=repo.searchByText(name);
		
		return searchDoc;
	}
	
	public List<ProductSearchDocument> searchByCategory(String category){
		List<ProductSearchDocument> searchDoc=repo.findByCategory(category);
		
		return searchDoc;
	}
	
	public List<ProductSearchDocument> getAllProducts(){
		return (List<ProductSearchDocument>) repo.findAll();
	}
	
	public void deleteById(Long id) {
		repo.deleteById(id);
    }
	
	//Converting product to productdoc to store in elasticrepo 
	private ProductSearchDocument mapDocument(Product  product) {
		ProductSearchDocument doc=new ProductSearchDocument();
		doc.setId(product.getId());
		doc.setName(product.getName());
		doc.setDescription(product.getDescription());
		doc.setCategory(product.getCategory());
		doc.setPrice(product.getPrice());
		
		return doc;
	}
}
