package com.hermes.market.web.controller;

import com.hermes.market.application.dto.response.PromotionResponse;
import com.hermes.market.application.service.PromotionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/{id}")
    public ResponseEntity<PromotionResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(promotionService.findById(id));
    }
}

