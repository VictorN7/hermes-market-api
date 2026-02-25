package com.hermes.market.web.controller;

import com.hermes.market.application.service.BrandService;
import com.hermes.market.domain.product.Brand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService){
        this.brandService =  brandService;
    }

    @GetMapping
    public ResponseEntity<List<Brand>> findAll(){
     return ResponseEntity.ok().body(brandService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brand> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(brandService.findById(id));
    }
}
