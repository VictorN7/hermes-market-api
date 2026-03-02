package com.hermes.market.web.controller;

import java.math.BigDecimal;
import java.util.List;

import com.hermes.market.application.dto.response.ProductResponse;
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
	
//	@GetMapping
//	public ResponseEntity<List<ProductResponse>> findAll(){
//		return ResponseEntity.ok().body(productService.findAll());
//	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> findProductById(@PathVariable Long id){
			return ResponseEntity.ok().body(productService.findById(id));
	}

	@GetMapping
	public ResponseEntity<List<ProductResponse>> findAll(
			@RequestParam(name = "category", required = false, defaultValue = "0") Long categoryId,
			@RequestParam(name = "brand", required = false, defaultValue = "0") Long brandId,
			@RequestParam(name = "name", required = false, defaultValue = "0") String productName,
			@RequestParam(name = "brandId", required = false, defaultValue = "0") BigDecimal minPrice,
			@RequestParam(name = "brandId", required = false, defaultValue = "0.0") BigDecimal maxPrice,
			@RequestParam(name = "onSale", required = false, defaultValue = "false") Boolean onSale
			){

		return ResponseEntity.ok().body(productService.findAll(categoryId, brandId, productName, minPrice, maxPrice, onSale));
	}
}