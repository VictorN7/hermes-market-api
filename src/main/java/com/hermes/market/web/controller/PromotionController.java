package com.hermes.market.web.controller;

import com.hermes.market.application.dto.request.ProductIdRequest;
import com.hermes.market.application.dto.request.PromotionRequest;
import com.hermes.market.application.dto.response.ProductResponse;
import com.hermes.market.application.dto.response.PromotionResponse;
import com.hermes.market.application.service.PromotionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/promotions")
@Validated
public class PromotionController {

    private final PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @GetMapping
    public ResponseEntity<Page<PromotionResponse>> findAll(@PageableDefault(size = 10, sort = "id") Pageable pageable){
        return ResponseEntity.ok().body(promotionService.findAll(pageable));
    }

    @GetMapping("/inactive")
    public ResponseEntity<Page<PromotionResponse>> findInactivePromotions(@PageableDefault(size = 10, sort = "id") Pageable pageable){
        return ResponseEntity.ok().body(promotionService.findAllInactive(pageable));
    }

    @GetMapping("/inactive/{id}")
    public ResponseEntity<PromotionResponse> findInactivePromotionById(@PathVariable @Positive Long id){
        return ResponseEntity.ok().body(promotionService.findInactiveById(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PromotionResponse> findById(@PathVariable @Positive Long id){
        return ResponseEntity.ok().body(promotionService.findById(id));
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<Page<ProductResponse>> findProductsByPromotion(@PathVariable @Positive Long id, @PageableDefault(size = 10, sort = "id") Pageable pageable){
        return ResponseEntity.ok().body(promotionService.findProductsByPromotion(id, pageable));
    }

    @PostMapping
    public ResponseEntity<PromotionResponse> createPromotion(@RequestBody @Valid PromotionRequest promotionRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(promotionService.createPromotion(promotionRequest));
    }

    @PostMapping("/{promotionId}/products")
    public ResponseEntity<Page<ProductResponse>> insertProduct(@PathVariable @Positive Long promotionId, @RequestBody @Valid ProductIdRequest request, Pageable pageable){
        return ResponseEntity.status(HttpStatus.CREATED).body(promotionService.insertProduct(request.getProductId(), promotionId, pageable));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivatePromotion(@PathVariable @Positive Long id){
        promotionService.deactivatePromotion(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activatePromotion(@PathVariable @Positive Long id){
        promotionService.activatePromotion(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{promotionId}/products/{productId}")
    public ResponseEntity<PromotionResponse> deleteProduct(@PathVariable @Positive Long promotionId, @PathVariable @Positive Long productId){
        return ResponseEntity.ok().body(promotionService.deleteProduct(promotionId, productId));
    }

}

