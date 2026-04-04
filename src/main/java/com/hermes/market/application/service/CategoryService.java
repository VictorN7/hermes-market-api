package com.hermes.market.application.service;

import java.util.List;

import com.hermes.market.application.dto.request.CategoryRequest;
import com.hermes.market.application.dto.response.CategoryResponse;
import com.hermes.market.application.exception.BusinessException;
import com.hermes.market.application.exception.ResourceNotFoundException;
import com.hermes.market.application.mapper.CategoryMapper;
import com.hermes.market.domain.product.Category;
import com.hermes.market.domain.product.CategoryStatus;
import com.hermes.market.domain.product.Product;
import com.hermes.market.infrastructure.repository.ProductRepository;
import org.springframework.stereotype.Service;
import com.hermes.market.infrastructure.repository.CategoryRepository;

@Service
public class CategoryService {
	
	private final CategoryRepository categoryRepository;
	private final ProductRepository productRepository;
	
	public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
	}
	
	public List<CategoryResponse> findAll(){
		return categoryRepository.findAll()
				.stream()
				.map(CategoryMapper::toResponse)
				.toList();
	}
	
	public CategoryResponse findById(Long id) {
		return CategoryMapper.toResponse(categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found")));
	}

	public CategoryResponse createCategory(CategoryRequest categoryRequest){
		return CategoryMapper.toResponse(categoryRepository.save(CategoryMapper.toCreate(categoryRequest)));
	}

	public CategoryResponse updateCategoryName(Long categoryId, CategoryRequest categoryRequest){

		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found"));

		if (!category.getName().equalsIgnoreCase(categoryRequest.getName()) && categoryRepository.existsByNameIgnoreCase(categoryRequest.getName())){
			throw new BusinessException("Category name already exists");
		}

		category.updateName(categoryRequest.getName());

		return CategoryMapper.toResponse(categoryRepository.save(category));
	}

	public void deactivateCategory(Long categoryId){

		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
		category.deactivate();
		categoryRepository.save(category);
	}

	public void activateCategory(Long categoryId){

		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
		category.activate();
		categoryRepository.save(category);
	}

	public void deleteOrDeactivateCategory(Long categoryId){

		Category category =  categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found"));

		if (productRepository.existsByCategoryId(categoryId)){
			category.deactivate();
			categoryRepository.save(category);
		} else {
			categoryRepository.delete(category);
		}
	}

	public List<CategoryResponse> findInactiveCategories(){
		return categoryRepository.findByStatus(CategoryStatus.INACTIVE).stream().map(CategoryMapper::toResponse).toList();
	}

	public CategoryResponse findInactiveCategoryById(Long id){

		Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));

		if(!CategoryStatus.INACTIVE.equals(category.getStatus())){
			throw new ResourceNotFoundException("Inactive category not found");
		}

		return CategoryMapper.toResponse(category);
	}

}
