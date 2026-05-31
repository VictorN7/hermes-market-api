package com.hermes.market.web.controller.client;

import com.hermes.market.application.dto.request.BrandRequest;
import com.hermes.market.application.dto.response.BrandDetailResponse;
import com.hermes.market.application.dto.response.BrandMenuResponse;
import com.hermes.market.application.dto.response.ProductResponse;
import com.hermes.market.application.service.BrandService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public ResponseEntity<Page<BrandMenuResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(brandService.findAll(pageable));
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<Page<ProductResponse>> findProductsByBrandId(@PathVariable @Positive Long id, Pageable pageable) {
        return ResponseEntity.ok().body(brandService.findProductsByBrandId(id, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDetailResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(brandService.findById(id));
    }

}
