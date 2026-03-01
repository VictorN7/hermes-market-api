package com.hermes.market.application.service;

import java.util.List;

import com.hermes.market.application.dto.response.ProductResponse;
import com.hermes.market.application.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import com.hermes.market.infrastructure.repository.ProductRepository;

@Service
public class ProductService {
	
	private final ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public List<ProductResponse> findAll(){
		return productRepository.findAll().stream().map(ProductMapper::toResponse).toList();
	}
	
	public ProductResponse findById(Long id) {
		return ProductMapper.toResponse(productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found!")));
	}
}
