package com.hermes.market.application.service;

import com.hermes.market.application.dto.request.CategoryRequest;
import com.hermes.market.application.dto.response.CategoryResponse;
import com.hermes.market.application.exception.BusinessException;
import com.hermes.market.application.exception.ResourceNotFoundException;
import com.hermes.market.application.mapper.CategoryMapper;
import com.hermes.market.domain.product.Category;
import com.hermes.market.domain.product.CategoryStatus;
import com.hermes.market.infrastructure.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.hermes.market.infrastructure.repository.CategoryRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {
	
	private final CategoryRepository categoryRepository;
	private final ProductRepository productRepository;
	
	public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
	}

	@Transactional(readOnly = true)
	public Page<CategoryResponse> findAll(Pageable pageable) {
		Page<Category> categories = categoryRepository.findAllByStatus(CategoryStatus.ACTIVE.getCode(), pageable);
		return categories.map(CategoryMapper::toResponse);
	}

	@Transactional(readOnly = true)
	public CategoryResponse findById(Long id) {

		if(id <= 0){
			throw new IllegalArgumentException("Category ID must be positive");
		}

		return CategoryMapper.toResponse(categoryRepository.findByIdAndStatus(id, CategoryStatus.ACTIVE.getCode())
				.orElseThrow(() -> new ResourceNotFoundException("Category not found")));
	}

	@Transactional
	public CategoryResponse createCategory(CategoryRequest categoryRequest){

		if (categoryRepository.existsByNameIgnoreCase(categoryRequest.getName().trim().replaceAll("\\s+", " "))) {
			throw new BusinessException("Category name already exists");
		}
		return CategoryMapper.toResponse(categoryRepository.save(CategoryMapper.toCreate(categoryRequest)));
	}

	@Transactional
	public CategoryResponse updateCategoryName(Long id, CategoryRequest categoryRequest){

		if(id <= 0){
			throw new IllegalArgumentException("Category ID must be positive");
		}

		Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));

		if (!category.getName().equalsIgnoreCase(categoryRequest.getName().trim().replaceAll("\\s+", " "))
				&& categoryRepository.existsByNameIgnoreCase(categoryRequest.getName().trim().replaceAll("\\s+", " "))){
			throw new BusinessException("Category name already exists");
		}

		category.updateName(categoryRequest.getName().trim().replaceAll("\\s+", " "));

		return CategoryMapper.toResponse(categoryRepository.save(category));
	}

	@Transactional
	public void deactivateCategory(Long id){

		if(id <= 0){
			throw new IllegalArgumentException("Category ID must be positive");
		}

		Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
		category.deactivate();
		categoryRepository.save(category);
	}

	@Transactional
	public void activateCategory(Long id){

		if(id <= 0){
			throw new IllegalArgumentException("Category ID must be positive");
		}

		Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
		category.activate();
		categoryRepository.save(category);
	}

	@Transactional
	public void deleteOrDeactivateCategory(Long id){

		if(id <= 0){
			throw new IllegalArgumentException("Category ID must be positive");
		}

		Category category =  categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));

		if (productRepository.existsByCategoryId(id)){
			category.deactivate();
			categoryRepository.save(category);
		} else {
			categoryRepository.delete(category);
		}
	}

	@Transactional(readOnly = true)
	public Page<CategoryResponse> findInactiveCategories(Pageable pageable){
		 Page<Category> categories = categoryRepository.findAllByStatus(CategoryStatus.INACTIVE.getCode(), pageable);
		 return categories.map(CategoryMapper::toResponse);
	}

	@Transactional(readOnly = true)
	public CategoryResponse findInactiveCategoryById(Long id){

		if(id <= 0){
			throw new IllegalArgumentException("Category ID must be positive");
		}

		Category category = categoryRepository.findByIdAndStatus(id,
				CategoryStatus.INACTIVE.getCode()).orElseThrow(() -> new ResourceNotFoundException("Category not found"));

		return CategoryMapper.toResponse(category);
	}

}
