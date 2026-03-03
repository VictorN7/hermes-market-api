package com.hermes.market.web.controller;

import java.util.List;

import com.hermes.market.application.dto.filter.ProductFilter;
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
	public ResponseEntity<List<ProductSummaryResponse>> findAll(ProductFilter productFilter){

		return ResponseEntity.ok().body(productService.findAll(productFilter));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> findProductById(@PathVariable Long id){
			return ResponseEntity.ok().body(productService.findById(id));
	}
}