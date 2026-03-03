package com.hermes.market.web.controller;

import java.math.BigDecimal;
import java.util.List;

import com.hermes.market.application.dto.response.ProductResponse;
import com.hermes.market.application.dto.response.ProductSummaryResponse;
import com.hermes.market.application.service.ProductService;
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
	public ResponseEntity<List<ProductSummaryResponse>> findAll(
			@RequestParam(name = "category", required = false) Long categoryId,
			@RequestParam(name = "brand", required = false) Long brandId,
			@RequestParam(name = "name", required = false) String productName,
			@RequestParam(name = "onSale", required = false) Boolean onSale
	){

		return ResponseEntity.ok().body(productService.findAll(categoryId, brandId, productName, onSale));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> findProductById(@PathVariable Long id){
			return ResponseEntity.ok().body(productService.findById(id));
	}
}