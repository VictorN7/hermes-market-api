package com.hermes.market.web.controller.client;

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

    @GetMapping("/{id}")
    public ResponseEntity<PromotionResponse> findById(@PathVariable @Positive Long id){
        return ResponseEntity.ok().body(promotionService.findById(id));
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<Page<ProductResponse>> findProductsByPromotion(@PathVariable @Positive Long id, Pageable pageable){
        return ResponseEntity.ok().body(promotionService.findProductsByPromotion(id, pageable));
    }

}

