package com.hermes.market.application.service;

import java.util.List;

import com.hermes.market.application.dto.filter.ProductFilter;
import com.hermes.market.application.dto.request.ProductRequest;
import com.hermes.market.application.dto.request.ProductStockUpdateRequest;
import com.hermes.market.application.dto.request.ProductUpdateRequest;
import com.hermes.market.application.dto.response.ProductResponse;
import com.hermes.market.application.dto.response.ProductSummaryResponse;
import com.hermes.market.application.exception.ResourceNotFoundException;
import com.hermes.market.application.mapper.ProductMapper;
import com.hermes.market.domain.product.Brand;
import com.hermes.market.domain.product.Category;
import com.hermes.market.domain.product.Product;
import com.hermes.market.infrastructure.repository.BrandRepository;
import com.hermes.market.infrastructure.repository.CategoryRepository;
import com.hermes.market.infrastructure.repository.specification.ProductSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.hermes.market.infrastructure.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, BrandRepository brandRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
    }

    public List<ProductSummaryResponse> findAll(ProductFilter productFilter) {

        Specification<Product> spec = Specification.
                where(ProductSpecification.categoryEqual(productFilter.getCategoryId()))
                .and(ProductSpecification.brandEqual(productFilter.getBrandId()))
                .and(ProductSpecification.nameProductLike(productFilter.getName()))
                .and(ProductSpecification.containsPromotion(productFilter.getOnSale()));

        return productRepository.findAll(spec).stream().map(ProductMapper::toSummary).toList();
    }

    public ProductResponse findById(Long id) {
        return ProductMapper.toResponse(productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found!")));
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        return ProductMapper.toResponse(productRepository.save(ProductMapper.toCreate(productRequest,
                categoryRepository.findById(productRequest.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category not found!")),
                brandRepository.findById(productRequest.getBrandId()).orElseThrow(() -> new ResourceNotFoundException("Brand not found!")))));
    }

    public ProductResponse updateProduct(Long id, ProductUpdateRequest productUpdateRequest) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found!"));

        Category category = categoryRepository.findById(productUpdateRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found!"));

        Brand brand = brandRepository.findById(productUpdateRequest.getBrandId())
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found!"));

        product.updateProduct(productUpdateRequest.getName(), productUpdateRequest.getDescription(),
                productUpdateRequest.getPrice(), productUpdateRequest.getImgUrl(), category, brand);

        productRepository.save(product);

		return ProductMapper.toResponse(product);
    }

    public ProductResponse adjustStock(Long productId, ProductStockUpdateRequest request){

        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        product.adjustStock(request.getQuantity());
        productRepository.save(product);

        return ProductMapper.toResponse(product);
    }

    public void deactivateProduct(Long productId){

        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found!"));
        product.deactivate();
        productRepository.save(product);
    }

}
