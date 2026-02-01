package com.product_service.product.search.Doc.Document;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import jakarta.persistence.Id;

@Document(indexName="products")
public class ProductSearchDocument {

	@Id
	private Long id;
	
	@Field(type=FieldType.Text,analyzer = "standard")
	private String name;
	
	@Field(type=FieldType.Text,analyzer = "standard")
	private String description;
	
	@Field(type=FieldType.Keyword)
	private String category;
	
	@Field(type=FieldType.Double)
	private double price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
