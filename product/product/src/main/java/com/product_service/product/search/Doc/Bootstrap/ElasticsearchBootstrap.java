package com.product_service.product.search.Doc.Bootstrap;

import org.springframework.stereotype.Component;

import com.product_service.product.repository.ProductRepository;
import com.product_service.product.repository.ProductSearchRepository;
import com.product_service.product.search.Doc.ProductSearchDocument.ProductSearchMapper;

import jakarta.annotation.PostConstruct;

@Component
public class ElasticsearchBootstrap {

	private final ProductRepository productRepo;
    private final ProductSearchRepository searchRepo;
    private final ProductSearchMapper mapper;
    
    

    public ElasticsearchBootstrap(ProductRepository productRepo, ProductSearchRepository searchRepo,
			ProductSearchMapper mapper) {
		super();
		this.productRepo = productRepo;
		this.searchRepo = searchRepo;
		this.mapper = mapper;
	}



    @PostConstruct
    public void rebuildIndex() {
        if (searchRepo.count() == 0) {
            productRepo.findAll()
                       .forEach(p -> searchRepo.save(mapper.toDocument(p)));
        }
    }
}
