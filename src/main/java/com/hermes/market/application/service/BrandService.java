package com.hermes.market.application.service;

import com.hermes.market.application.dto.response.BrandResponse;
import com.hermes.market.application.mapper.BrandMapper;
import com.hermes.market.infrastructure.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository){
        this.brandRepository = brandRepository;
    }

    public List<BrandResponse> findAll(){
        return brandRepository.findAll().stream().map(BrandMapper::toResponse).toList();
    }

    public BrandResponse findById(Long id){
        return BrandMapper.toResponse(brandRepository.findById(id).orElseThrow( () -> new RuntimeException("Brand not found")));
    }
}

