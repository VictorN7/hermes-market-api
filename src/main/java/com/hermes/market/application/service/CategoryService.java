package com.hermes.market.application.service;

import java.util.List;

import com.hermes.market.application.dto.request.CategoryRequest;
import com.hermes.market.application.dto.response.CategoryResponse;
import com.hermes.market.application.exception.ResourceNotFoundException;
import com.hermes.market.application.mapper.CategoryMapper;
import org.springframework.stereotype.Service;
import com.hermes.market.infrastructure.repository.CategoryRepository;
import com.hermes.market.infrastructure.repository.ProductRepository;

@Service
public class CategoryService {
	
	private final CategoryRepository categoryRepository;
	
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	public List<CategoryResponse> findAll(){
		return categoryRepository.findAll()
				.stream()
				.map(CategoryMapper::toResponse)
				.toList();
	}
	
	public CategoryResponse findById(Long id) {
		return CategoryMapper.toResponse(categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found!")));
	}

	public CategoryResponse createCategory(CategoryRequest categoryRequest){
		return CategoryMapper.toResponse(categoryRepository.save(CategoryMapper.toCreate(categoryRequest)));
	}
}
