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

	@GetMapping("/inactive")
	public ResponseEntity<List<CategoryResponse>> findInactiveCategories(){
		return ResponseEntity.ok().body(categoryService.findAllCategoriesDeactivated());
	}

	@GetMapping("/inactive/{id}")
	public ResponseEntity<CategoryResponse> findInactiveCategoryById(@PathVariable Long id){
		return ResponseEntity.ok().body(categoryService.findInactiveCategoryById(id));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryResponse> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(categoryService.findById(id));
	}

	@PostMapping
	public ResponseEntity<CategoryResponse> createCategory(@RequestBody @Valid CategoryRequest categoryRequest){
		 return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(categoryRequest));
	}

	@PatchMapping("/{id}/name")
	public ResponseEntity<CategoryResponse> updateCategoryName(@PathVariable Long id, @RequestBody @Valid CategoryRequest categoryRequest){
		return ResponseEntity.ok().body(categoryService.updateCategoryName(id, categoryRequest));
	}

	@PatchMapping("/{id}/deactivate")
	public ResponseEntity<Void> deactivateCategory(@PathVariable Long id){
		categoryService.deactivateCategory(id);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/{id}/activate")
	public ResponseEntity<Void> activateCategory(@PathVariable Long id){
		categoryService.activateCategory(id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	 public ResponseEntity<Void> deleteOrDeactivateCategory(@PathVariable Long id){
		categoryService.deleteOrDeactivateCategory(id);
		return ResponseEntity.noContent().build();
	}

}