package com.hermes.market.web.controller.admin;


import com.hermes.market.application.dto.request.BrandRequest;
import com.hermes.market.application.dto.response.BrandDetailResponse;
import com.hermes.market.application.service.BrandService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/brands")
@Validated
public class AdminBrandController {

    private final BrandService brandService;

    public AdminBrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/inactive")
    public ResponseEntity<Page<BrandDetailResponse>> findInactiveBrands(Pageable pageable) {
        return ResponseEntity.ok().body(brandService.findInactiveBrands(pageable));
    }

    @GetMapping("/inactive/{id}")
    public ResponseEntity<BrandDetailResponse> findInactiveBrandById(@PathVariable Long id) {
        return ResponseEntity.ok().body(brandService.findInactiveBrandById(id));
    }

    @PostMapping
    public ResponseEntity<BrandDetailResponse> createBrand(@RequestBody @Valid BrandRequest brandRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(brandService.createBrand(brandRequest));
    }

    @PatchMapping("/{id}/name")
    public ResponseEntity<BrandDetailResponse> updateBrand(@PathVariable Long id, @RequestBody @Valid BrandRequest brandRequest) {
        return ResponseEntity.ok().body(brandService.updateBrand(id, brandRequest));
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activateBrand(@PathVariable Long id) {
        brandService.activateBrand(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateBrand(@PathVariable Long id) {
        brandService.deactivateBrand(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrDeactivateBrand(@PathVariable Long id) {
        brandService.deleteOrDeactivateBrand(id);
        return ResponseEntity.noContent().build();
    }

}
