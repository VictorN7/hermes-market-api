package com.hermes.market.application.service;

import java.math.BigDecimal;
import java.util.List;

import com.hermes.market.application.dto.response.ProductResponse;
import com.hermes.market.application.dto.response.ProductSummaryResponse;
import com.hermes.market.application.mapper.ProductMapper;
import com.hermes.market.infrastructure.repository.BrandRepository;
import com.hermes.market.infrastructure.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import com.hermes.market.infrastructure.repository.ProductRepository;

@Service
public class ProductService {
	
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	private final BrandRepository brandRepository;

	public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, BrandRepository brandRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		this.brandRepository = brandRepository;
	}
	
	public List<ProductSummaryResponse> findAll(Long categoryId, Long brandId, String productName, BigDecimal minPrice,
												BigDecimal maxPrice, Boolean onSale){
		return productRepository.findAll().stream().map(ProductMapper::toSummary).toList();
	}
	
	public ProductResponse findById(Long id) {
		return ProductMapper.toResponse(productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found!")));
	}
}
