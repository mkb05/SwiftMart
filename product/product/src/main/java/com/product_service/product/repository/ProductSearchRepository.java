package com.product_service.product.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.product_service.product.search.Document.ProductSearchDocument;


public interface ProductSearchRepository extends ElasticsearchRepository<ProductSearchDocument,Long> {

	List<ProductSearchDocument> findByNameContaining(String keyword);
	
	List<ProductSearchDocument> findByCategory(String category);
}
