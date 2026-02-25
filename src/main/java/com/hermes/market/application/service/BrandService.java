package com.hermes.market.application.service;

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

    public List<Brand> findAll(){
        return brandRepository.findAll();
    }

    public Brand findById(Long id){
        return brandRepository.findById(id).orElseThrow( () -> new RuntimeException("Brand not found!"));
    }
}

