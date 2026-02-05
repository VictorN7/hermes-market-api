package com.hermes.market.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hermes.market.domain.product.Category;
import com.hermes.market.domain.product.Product;
import com.hermes.market.infrastructure.repository.CategoryRepository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	private final CategoryRepository categoryRepository;
	
	public CategoryController(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	@GetMapping()
	public ResponseEntity<List<Category>> findAll() {
		
		List<Category> categories = categoryRepository.findAll();
		return ResponseEntity.ok(categories);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> findAll(@PathVariable Long id) {
		
		return categoryRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/{id}/products")
	public ResponseEntity<List<Product>> findProductsByCategory(@PathVariable Long id){
		
		return categoryRepository.findById(id)
				.map(category -> ResponseEntity.ok(category.getProducts()))
				.orElse(ResponseEntity.notFound().build());
	}
	
	
}
