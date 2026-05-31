package com.hermes.market.web.controller.client;

import com.hermes.market.application.dto.request.CategoryRequest;
import com.hermes.market.application.dto.response.CategoryResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.hermes.market.application.service.CategoryService;

@RestController
@RequestMapping("/api/v1/categories")
@Validated
public class CategoryController {
	
	private final CategoryService categoryService;
	
	public CategoryController(CategoryService categoryService) {
		this.categoryService= categoryService;
	}
	
	@GetMapping()
	public ResponseEntity<Page<CategoryResponse>> findAll(Pageable pageable) {
		return ResponseEntity.ok().body(categoryService.findAll(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryResponse> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(categoryService.findById(id));
	}

}