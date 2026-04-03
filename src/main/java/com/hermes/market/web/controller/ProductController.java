package com.hermes.market.web.controller;

import java.util.List;

import com.hermes.market.application.dto.filter.ProductFilter;
import com.hermes.market.application.dto.request.ProductRequest;
import com.hermes.market.application.dto.request.ProductStockUpdateRequest;
import com.hermes.market.application.dto.request.ProductUpdateRequest;
import com.hermes.market.application.dto.response.ProductResponse;
import com.hermes.market.application.dto.response.ProductSummaryResponse;
import com.hermes.market.application.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public ResponseEntity<List<ProductSummaryResponse>> findAll(ProductFilter productFilter){

		return ResponseEntity.ok().body(productService.findAll(productFilter));
	}

	@GetMapping("/inactive")
	public ResponseEntity<List<ProductResponse>> findInactiveProducts(){
		return ResponseEntity.ok().body(productService.findAllProductsDeactivated());
	}

	@GetMapping("/inactive/{id}")
	public ResponseEntity<ProductResponse> findInactiveProductById(@PathVariable Long id){
		return ResponseEntity.ok().body(productService.findProductDeactivatedById(id));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> findProductById(@PathVariable Long id){
			return ResponseEntity.ok().body(productService.findById(id));
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