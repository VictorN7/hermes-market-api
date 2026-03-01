package com.hermes.market.web.controller;

import java.util.List;

import com.hermes.market.application.dto.response.CategoryResponse;
import com.hermes.market.application.dto.response.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hermes.market.application.service.CategoryService;


@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
	
	private final CategoryService categoryService;
	
	public CategoryController(CategoryService categoryService) {
		this.categoryService= categoryService;
	}
	
	@GetMapping()
	public ResponseEntity<List<CategoryResponse>> findAll() {
		return ResponseEntity.ok().body(categoryService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryResponse> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(categoryService.findById(id));
	}
	
	@GetMapping("/{id}/products")
	public ResponseEntity<List<ProductResponse>> findProductsByCategory(@PathVariable Long id){
		return  ResponseEntity.ok().body(categoryService.findProductsByCategory(id));
	}
}