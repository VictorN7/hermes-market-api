package com.hermes.market.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hermes.market.domain.product.Product;
import com.hermes.market.infrastructure.repository.ProductRepository;


@RestController
@RequestMapping("/products")
public class ProductController {

	private final ProductRepository productRepository;
	
	public ProductController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@GetMapping()
	public ResponseEntity<List<Product>> findAll(){
		
		List<Product> products = productRepository.findAll();
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> findProductById(@PathVariable Long id){
			return productRepository.findById(id)
					.map(ResponseEntity::ok)
					.orElse(ResponseEntity.notFound().build());
	}
}