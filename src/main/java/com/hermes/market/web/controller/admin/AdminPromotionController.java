package com.hermes.market.web.controller.admin;


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
@RequestMapping("/api/v1/admin/promotions")
@Validated
public class AdminPromotionController {

    private final PromotionService promotionService;

    public  AdminPromotionController(PromotionService promotionService){
        this.promotionService = promotionService;
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<Page<ProductResponse>> findProductsByPromotion(@PathVariable @Positive Long id, @PageableDefault(size = 10, sort = "name") Pageable pageable){
        return ResponseEntity.ok().body(promotionService.findProductsByPromotionAdmin(id, pageable));
    }

    @GetMapping("/inactive")
    public ResponseEntity<Page<PromotionResponse>> findInactivePromotions(@PageableDefault(size = 10, sort = "id") Pageable pageable){
        return ResponseEntity.ok().body(promotionService.findAllInactive(pageable));
    }

    @GetMapping("/inactive/{id}")
    public ResponseEntity<PromotionResponse> findInactivePromotionById(@PathVariable @Positive Long id){
        return ResponseEntity.ok().body(promotionService.findInactiveById(id));
    }

    @PostMapping
    public ResponseEntity<PromotionResponse> createPromotion(@RequestBody @Valid PromotionRequest promotionRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(promotionService.createPromotion(promotionRequest));
    }

    @PostMapping("/{promotionId}/products")
    public ResponseEntity<Page<ProductResponse>> addProductToPromotion(@PathVariable @Positive Long promotionId, @RequestBody @Valid ProductIdRequest request, Pageable pageable){
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
