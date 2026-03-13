package com.hermes.market.web.controller;

import java.util.List;

import com.hermes.market.application.dto.request.CategoryRequest;
import com.hermes.market.application.dto.response.CategoryResponse;
import com.hermes.market.application.dto.response.ProductResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@PostMapping
	public ResponseEntity<CategoryResponse> createCategory(@RequestBody @Valid CategoryRequest categoryRequest){
		 return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(categoryRequest));
	}

}