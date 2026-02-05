package com.hermes.market.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hermes.market.domain.product.Category;
import com.hermes.market.domain.product.Product;
import com.hermes.market.infrastructure.repository.CategoryRepository;
import com.hermes.market.infrastructure.repository.ProductRepository;

@Service
public class CategoryService {
	
	private CategoryRepository categoryRepository;
	
	private ProductRepository productRepository;
	
	public CategoryService(CategoryRepository categoryRepository,ProductRepository productRepository) {
		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
	}
	
	public List<Category> findAll(){
		return categoryRepository.findAll();
	}
	
	public Category findById(Long id) {
		return categoryRepository.findById(id).orElseThrow();
	}
	
	public List<Product> findProductsByCategory(Long id){
		return productRepository.findByCategoryId(id);
	}
}
