package com.hermes.market.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hermes.market.domain.product.Product;
import com.hermes.market.infrastructure.repository.ProductRepository;

@Service
public class ProductService {
	
	private ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public List<Product> findAll(){
		return productRepository.findAll();
	}
	
	public Product findById(Long id) {
		return productRepository.findById(id).orElseThrow();
	}
}
