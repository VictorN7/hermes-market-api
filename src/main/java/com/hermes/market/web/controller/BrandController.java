package com.hermes.market.web.controller;

import com.hermes.market.application.dto.request.BrandRequest;
import com.hermes.market.application.dto.response.BrandDetailResponse;
import com.hermes.market.application.dto.response.BrandMenuResponse;
import com.hermes.market.application.service.BrandService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService){
        this.brandService =  brandService;
    }

    @GetMapping
    public ResponseEntity<List<BrandMenuResponse>> findAll(){
     return ResponseEntity.ok().body(brandService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDetailResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(brandService.findById(id));
    }

    @PostMapping
    public ResponseEntity<BrandDetailResponse> createBrand(@RequestBody @Valid BrandRequest brandRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(brandService.createBrand(brandRequest));
    }

}
