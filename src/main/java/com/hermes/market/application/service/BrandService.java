package com.hermes.market.application.service;

import com.hermes.market.application.dto.request.BrandRequest;
import com.hermes.market.application.dto.response.BrandDetailResponse;
import com.hermes.market.application.dto.response.BrandMenuResponse;
import com.hermes.market.application.exception.BusinessException;
import com.hermes.market.application.exception.ResourceNotFoundException;
import com.hermes.market.application.mapper.BrandMapper;
import com.hermes.market.domain.product.Brand;
import com.hermes.market.domain.product.BrandStatus;
import com.hermes.market.domain.product.Category;
import com.hermes.market.infrastructure.repository.BrandRepository;
import com.hermes.market.infrastructure.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;

    public BrandService(BrandRepository brandRepository, ProductRepository productRepository) {
        this.brandRepository = brandRepository;
        this.productRepository = productRepository;
    }

    public List<BrandMenuResponse> findAll() {
        return brandRepository.findAll().stream().map(BrandMapper::toMenu).toList();
    }

    public BrandDetailResponse findById(Long id) {
        return BrandMapper.toResponse(brandRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found")));
    }

    public BrandDetailResponse createBrand(BrandRequest brandRequest) {
        return BrandMapper.toResponse(brandRepository.save(BrandMapper.toCreate(brandRequest)));
    }

    public BrandDetailResponse updateBrand(Long brandId, BrandRequest brandRequest) {

        String newName = brandRequest.getName();
        Brand brand = brandRepository.findById(brandId).orElseThrow(() -> new ResourceNotFoundException("Brand not found"));

        if (!brand.getName().equalsIgnoreCase(newName) && brandRepository.existsByNameIgnoreCase(newName)) {
            throw new BusinessException("Brand name already exists");
        }

        brand.updateName(newName);
        return BrandMapper.toResponse(brand);
    }

    public void activateBrand(Long brandId) {

        Brand brand = brandRepository.findById(brandId).orElseThrow(() -> new ResourceNotFoundException("Brand not found"));
        brand.activate();
    }

    public void deactivateBrand(Long brandId) {

        Brand brand = brandRepository.findById(brandId).orElseThrow(() -> new ResourceNotFoundException("Brand not found"));
        brand.deactivate();
    }

    public void deleteOrDeactivateBrand(Long brandId) {

        Brand brand = brandRepository.findById(brandId).orElseThrow(() -> new ResourceNotFoundException("Brand not found"));

        if (productRepository.existsByBrandId(brandId)) {
            brand.deactivate();
            brandRepository.save(brand);
        } else {
            brandRepository.delete(brand);
        }
    }

    public List<BrandDetailResponse> findInactiveBrands() {
        return brandRepository.findByStatus(BrandStatus.INACTIVE).stream().map(BrandMapper::toResponse).toList();
    }

    public BrandDetailResponse findInactiveBrandById(Long brandId) {

        Brand brand = brandRepository.findById(brandId).orElseThrow(() -> new ResourceNotFoundException("Brand not found"));

        if (!BrandStatus.INACTIVE.equals(brand.getStatus())) {
            throw new BusinessException("Inactive brand not found");
        }
        return BrandMapper.toResponse(brand);
    }

}