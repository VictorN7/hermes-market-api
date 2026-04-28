package com.hermes.market.application.service;

import com.hermes.market.application.dto.request.BrandRequest;
import com.hermes.market.application.dto.response.BrandDetailResponse;
import com.hermes.market.application.dto.response.BrandMenuResponse;
import com.hermes.market.application.exception.BusinessException;
import com.hermes.market.application.exception.ResourceNotFoundException;
import com.hermes.market.application.mapper.BrandMapper;
import com.hermes.market.domain.product.Brand;
import com.hermes.market.domain.product.BrandStatus;
import com.hermes.market.infrastructure.repository.BrandRepository;
import com.hermes.market.infrastructure.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BrandService {

    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;

    public BrandService(BrandRepository brandRepository, ProductRepository productRepository) {
        this.brandRepository = brandRepository;
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public Page<BrandMenuResponse> findAll(Pageable pageable) {
        Page<Brand> brands = brandRepository.findAll(pageable);
        return brands.map(BrandMapper::toMenu);
    }

    @Transactional(readOnly = true)
    public BrandDetailResponse findById(Long id) {

        if(id <= 0 ){
            throw new IllegalArgumentException("Brand ID must be positive");
        }

        return BrandMapper.toResponse(brandRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found")));
    }

    @Transactional
    public BrandDetailResponse createBrand(BrandRequest brandRequest) {

        if (brandRepository.existsByNameIgnoreCase(brandRequest.getName())) {
            throw new BusinessException("Brand name already exists");
        }

        return BrandMapper.toResponse(brandRepository.save(BrandMapper.toCreate(brandRequest)));
    }

    @Transactional
    public BrandDetailResponse updateBrand(Long brandId, BrandRequest brandRequest) {

        String newName = brandRequest.getName();
        Brand brand = brandRepository.findById(brandId).orElseThrow(() -> new ResourceNotFoundException("Brand not found"));

        if (!brand.getName().equalsIgnoreCase(newName) && brandRepository.existsByNameIgnoreCase(newName)) {
            throw new BusinessException("Brand name already exists");
        }

        brand.updateName(newName);
        brandRepository.save(brand);

        return BrandMapper.toResponse(brand);
    }

    @Transactional
    public void activateBrand(Long brandId) {

        Brand brand = brandRepository.findById(brandId).orElseThrow(() -> new ResourceNotFoundException("Brand not found"));
        brand.activate();
        brandRepository.save(brand);
    }

    @Transactional
    public void deactivateBrand(Long id) {

        if(id <= 0 ){
            throw new IllegalArgumentException("Brand ID must be positive");
        }

        Brand brand = brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Brand not found"));
        brand.deactivate();
        brandRepository.save(brand);
    }

    @Transactional
    public void deleteOrDeactivateBrand(Long id) {

        if(id <= 0 ){
            throw new IllegalArgumentException("Brand ID must be positive");
        }

        Brand brand = brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Brand not found"));

        if (productRepository.existsByBrandId(id)) {
            brand.deactivate();
            brandRepository.save(brand);
        } else {
            brandRepository.delete(brand);
        }
    }

    @Transactional(readOnly = true)
    public Page<BrandDetailResponse> findInactiveBrands(Pageable pageable) {
        Page<Brand> brands = brandRepository.findByStatus(BrandStatus.INACTIVE.getCode(), pageable);
        return brands.map(BrandMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public BrandDetailResponse findInactiveBrandById(Long id) {

        if(id <= 0 ){
            throw new IllegalArgumentException("Brand ID must be positive");
        }

        Brand brand = brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Brand not found"));

        if (!BrandStatus.INACTIVE.equals(brand.getStatus())) {
            throw new ResourceNotFoundException("Inactive brand not found");
        }
        return BrandMapper.toResponse(brand);
    }

}