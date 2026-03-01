package com.hermes.market.application.service;

import com.hermes.market.application.dto.response.BrandMenuResponse;
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

    public List<BrandMenuResponse> findAll(){
        return brandRepository.findAll().stream().map(BrandMapper::toResponse).toList();
    }

    public BrandMenuResponse findById(Long id){
        return BrandMapper.toResponse(brandRepository.findById(id).orElseThrow( () -> new RuntimeException("Brand not found")));
    }
}

