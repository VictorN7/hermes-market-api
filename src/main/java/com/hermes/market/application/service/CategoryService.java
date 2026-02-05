package com.hermes.market.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hermes.market.domain.product.Category;
import com.hermes.market.infrastructure.repository.CategoryRepository;

@Service
public class CategoryService {
	
	private CategoryRepository categoryRepository;
	
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	public List<Category> findAll(){
		return categoryRepository.findAll();
	}
	
	public Category findById(Long id) {
		return categoryRepository.findById(id).orElseThrow();
	}
}
