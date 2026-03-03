package com.hermes.market.application.service;

import java.util.List;

import com.hermes.market.application.dto.filter.ProductFilter;
import com.hermes.market.application.dto.response.ProductResponse;
import com.hermes.market.application.dto.response.ProductSummaryResponse;
import com.hermes.market.application.mapper.ProductMapper;
import com.hermes.market.domain.product.Product;
import com.hermes.market.infrastructure.repository.BrandRepository;
import com.hermes.market.infrastructure.repository.CategoryRepository;
import com.hermes.market.infrastructure.repository.specification.ProductSpecification;
import org.springframework.data.jpa.domain.Specification;
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
	
	public List<ProductSummaryResponse> findAll(ProductFilter productFilter){

		Specification<Product> spec = Specification.
				where(ProductSpecification.categoryEqual(productFilter.getCategoryId()))
				.and(ProductSpecification.brandEqual(productFilter.getBrandId()))
				.and(ProductSpecification.nameProductLike(productFilter.getName()));

		return productRepository.findAll(spec).stream().map(ProductMapper::toSummary).toList();
	}
	
	public ProductResponse findById(Long id) {
		return ProductMapper.toResponse(productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found!")));
	}
}
