package com.product_service.product.repository;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.product_service.product.search.Doc.Document.ProductSearchDocument;


public interface ProductSearchRepository extends ElasticsearchRepository<ProductSearchDocument,Long> {

	@Query("""
			{
			  "multi_match": {
			    "query": "?0",
			    "fields": ["name^2", "description"],
			    "operator": "and",
			    "fuzziness": "AUTO"
			  }
			}
			""")
	
	List<ProductSearchDocument> searchByText(String keyword);
	
	List<ProductSearchDocument> findByCategory(String category);
	
	List<ProductSearchDocument> findByIdIn(List<Long> ids);
}
