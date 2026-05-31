package com.hermes.market.web.controller.client;

import com.hermes.market.application.dto.request.CategoryRequest;
import com.hermes.market.application.dto.response.CategoryResponse;
import com.hermes.market.application.dto.response.ProductResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

	@GetMapping("/{id}/products")
	public ResponseEntity<Page<ProductResponse>> findProductsByCategoryId(@PathVariable @Positive Long id, @PageableDefault(sort = "name") Pageable pageable) {
		return ResponseEntity.ok().body(categoryService.findProductsByCategoryId(id, pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryResponse> findById(@PathVariable @Positive Long id) {
		return ResponseEntity.ok().body(categoryService.findById(id));
	}

}