package com.hermes.market.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hermes.market.application.service.CategoryService;
import com.hermes.market.domain.product.Category;
import com.hermes.market.domain.product.Product;


@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	private final CategoryService categoryService;
	
	public CategoryController(CategoryService categoryService) {
		this.categoryService= categoryService;
	}
	
	@GetMapping()
	public ResponseEntity<List<Category>> findAll() {
		List<Category> categories = categoryService.findAll();
		return ResponseEntity.ok(categories);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(categoryService.findById(id));
	}
	
	@GetMapping("/{id}/products")
	public ResponseEntity<List<Product>> findProductsByCategory(@PathVariable Long id){
		return  ResponseEntity.ok().body(categoryService.findProductsByCategory(id));
	}
}