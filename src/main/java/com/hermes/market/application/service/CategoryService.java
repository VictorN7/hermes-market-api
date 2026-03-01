package com.hermes.market.application.service;

import java.util.List;

import com.hermes.market.application.dto.response.CategoryResponse;
import com.hermes.market.application.dto.response.ProductResponse;
import com.hermes.market.application.mapper.CategoryMapper;
import com.hermes.market.application.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import com.hermes.market.domain.product.Category;
import com.hermes.market.infrastructure.repository.CategoryRepository;
import com.hermes.market.infrastructure.repository.ProductRepository;

@Service
public class CategoryService {
	
	private final CategoryRepository categoryRepository;
	private final ProductRepository productRepository;
	
	public CategoryService(CategoryRepository categoryRepository,ProductRepository productRepository) {
		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
	}
	
	public List<CategoryResponse> findAll(){
		return categoryRepository.findAll()
				.stream()
				.map(CategoryMapper::toResponse)
				.toList();
	}
	
	public CategoryResponse findById(Long id) {
		return CategoryMapper.toResponse(categoryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Category not found!")));
	}
	
	public List<ProductResponse> findProductsByCategory(Long id){

		Category category = categoryRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Category not found!"));

		return productRepository.findByCategoryId(id)
				.stream()
				.map(ProductMapper::toResponse)
				.toList();
	}
}
