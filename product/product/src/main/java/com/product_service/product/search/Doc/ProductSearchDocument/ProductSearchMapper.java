package com.product_service.product.search.Doc.ProductSearchDocument;

import org.springframework.stereotype.Component;

import com.product_service.product.Entity.Product;
import com.product_service.product.search.Doc.Document.ProductSearchDocument;

@Component
public class ProductSearchMapper {

	 public ProductSearchDocument toDocument(Product product) {
	        ProductSearchDocument doc = new ProductSearchDocument();
	        doc.setId(product.getId());
	        doc.setName(product.getName());
	        doc.setDescription(product.getDescription());
	        doc.setCategory(product.getCategory());
	        doc.setPrice(product.getPrice());
	        return doc;
	    }
}
