package com.hermes.market.web.controller.admin;


import com.hermes.market.application.dto.request.ProductRequest;
import com.hermes.market.application.dto.request.ProductStockUpdateRequest;
import com.hermes.market.application.dto.request.ProductUpdateRequest;
import com.hermes.market.application.dto.response.ProductResponse;
import com.hermes.market.application.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/products")
@Validated
public class AdminProductController {

    private final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/inactive")
    public ResponseEntity<Page<ProductResponse>> findInactiveProducts(Pageable pageable) {
        return ResponseEntity.ok().body(productService.findInactiveProducts(pageable));
    }

    @GetMapping("/inactive/{id}")
    public ResponseEntity<ProductResponse> findInactiveProductById(@PathVariable Long id){
        return ResponseEntity.ok().body(productService.findInactiveProductById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest productRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductUpdateRequest productUpdateRequest){
        return ResponseEntity.ok().body(productService.updateProduct(id, productUpdateRequest));
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductResponse> adjustStock(@PathVariable Long id, @RequestBody @Valid ProductStockUpdateRequest request){
        return ResponseEntity.ok().body(productService.adjustStock(id, request));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateProduct(@PathVariable Long id){
        productService.deactivateProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activateProduct(@PathVariable Long id){
        productService.activateProduct(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrDeactivateProduct(@PathVariable Long id){
        productService.deleteOrDeactivateProduct(id);
        return ResponseEntity.noContent().build();
    }

}
