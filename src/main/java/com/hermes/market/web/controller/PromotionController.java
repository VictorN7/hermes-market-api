package com.hermes.market.web.controller;

import com.hermes.market.application.dto.request.PromotionRequest;
import com.hermes.market.application.dto.response.PromotionResponse;
import com.hermes.market.application.service.PromotionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/promotions")
public class PromotionController {

    private final PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @GetMapping
    public ResponseEntity<List<PromotionResponse>> findAll(){
        return ResponseEntity.ok().body(promotionService.findAll());
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<PromotionResponse>> findInactivePromotions(){
        return ResponseEntity.ok().body(promotionService.findAllPromotionsDeactivated());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PromotionResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(promotionService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PromotionResponse> createPromotion(@RequestBody @Valid PromotionRequest promotionRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(promotionService.createPromotion(promotionRequest));
    }

    @PostMapping("/{promotionId}/products/{productId}")
    public ResponseEntity<PromotionResponse> insertProduct(@PathVariable Long promotionId, @PathVariable Long productId){
        return ResponseEntity.status(HttpStatus.CREATED).body(promotionService.insertProduct(productId, promotionId));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivatePromotion(@PathVariable Long id){
        promotionService.deactivatePromotion(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activatePromotion(@PathVariable Long id){
        promotionService.activatePromotion(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{promotionId}/products/{productId}")
    public ResponseEntity<PromotionResponse> deleteProduct(@PathVariable Long promotionId, @PathVariable Long productId){
        return ResponseEntity.ok().body(promotionService.deleteProduct(promotionId, productId));
    }

}

