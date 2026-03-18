package com.hermes.market.application.service;

import com.hermes.market.application.dto.request.BrandRequest;
import com.hermes.market.application.dto.response.BrandDetailResponse;
import com.hermes.market.application.dto.response.BrandMenuResponse;
import com.hermes.market.application.exception.BusinessException;
import com.hermes.market.application.exception.ResourceNotFoundException;
import com.hermes.market.application.mapper.BrandMapper;
import com.hermes.market.domain.product.Brand;
import com.hermes.market.infrastructure.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository){
        this.brandRepository = brandRepository;
    }

    public List<BrandMenuResponse> findAll(){
        return brandRepository.findAll().stream().map(BrandMapper::toMenu).toList();
    }

    public BrandDetailResponse findById(Long id){
        return BrandMapper.toResponse(brandRepository
                .findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Brand not found")));
    }

    public BrandDetailResponse createBrand(BrandRequest brandRequest){
        return BrandMapper.toResponse(brandRepository.save(BrandMapper.toCreate(brandRequest)));
    }

    public BrandDetailResponse updateBrand(Long brandId, BrandRequest brandRequest){

        String newName = brandRequest.getName();
        Brand brand = brandRepository.findById(brandId).orElseThrow(() -> new ResourceNotFoundException("Brand not found"));

        if (!brand.getName().equalsIgnoreCase(newName) && brandRepository.existsByNameIgnoreCase(newName)) {
            throw new BusinessException("Brand name already exists");
        }

        brand.updateName(newName);
        return BrandMapper.toResponse(brand);
    }

    public void activateBrand(Long brandId){

        Brand brand = brandRepository.findById(brandId).orElseThrow(() -> new ResourceNotFoundException("Brand not found"));
        brand.activateBrand();
    }

    public void deactivateBrand(Long brandId){

        Brand brand = brandRepository.findById(brandId).orElseThrow(() -> new ResourceNotFoundException("Brand not found"));
        brand.deactivateBrand();
    }

}