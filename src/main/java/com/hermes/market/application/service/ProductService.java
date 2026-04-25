package com.hermes.market.application.service;


import com.hermes.market.application.dto.filter.ProductFilter;
import com.hermes.market.application.dto.request.ProductRequest;
import com.hermes.market.application.dto.request.ProductStockUpdateRequest;
import com.hermes.market.application.dto.request.ProductUpdateRequest;
import com.hermes.market.application.dto.response.ProductResponse;
import com.hermes.market.application.dto.response.ProductSummaryResponse;
import com.hermes.market.application.exception.BusinessException;
import com.hermes.market.application.exception.ResourceNotFoundException;
import com.hermes.market.application.mapper.ProductMapper;
import com.hermes.market.domain.product.Brand;
import com.hermes.market.domain.product.Category;
import com.hermes.market.domain.product.Product;
import com.hermes.market.domain.product.ProductStatus;
import com.hermes.market.infrastructure.repository.*;
import com.hermes.market.infrastructure.repository.specification.ProductSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final OrderItemRepository orderItemRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository,
                          BrandRepository brandRepository, OrderItemRepository orderItemRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional(readOnly = true)
    public Page<ProductSummaryResponse> findAll(ProductFilter productFilter, Pageable pageable) {

        Specification<Product> spec = Specification.
                where(ProductSpecification.statusEqual(ProductStatus.ACTIVE.getCode()))
                .and(ProductSpecification.categoryEqual(productFilter.getCategoryId()))
                .and(ProductSpecification.brandEqual(productFilter.getBrandId()))
                .and(ProductSpecification.nameProductLike(productFilter.getName()))
                .and(ProductSpecification.containsPromotion(productFilter.getOnSale()));

        Page<Product> products =  productRepository.findAll(spec, pageable);

        return products.map(ProductMapper::toSummary);
    }

    @Transactional(readOnly = true)
    public ProductResponse findById(Long id) {

        if (id <= 0){
            throw new IllegalArgumentException("Product ID must be positive");
        }

        return ProductMapper.toResponse(productRepository.findByIdAndStatus(id, ProductStatus.ACTIVE.getCode())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found")));
    }

    @Transactional
    public ProductResponse createProduct(ProductRequest productRequest) {

        if (productRepository.existsByNameIgnoreCase(productRequest.getName().trim())) {
            throw new BusinessException("Product name already exists");
        }

        return ProductMapper.toResponse(productRepository.save(ProductMapper.toCreate(productRequest,
                categoryRepository.findById(productRequest.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category not found")),
                brandRepository.findById(productRequest.getBrandId()).orElseThrow(() -> new ResourceNotFoundException("Brand not found")))));
    }

    @Transactional
    public ProductResponse updateProduct(Long id, ProductUpdateRequest productUpdateRequest) {

        if (id <= 0){
            throw new IllegalArgumentException("Product ID must be positive");
        }

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        Category category = categoryRepository.findById(productUpdateRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Brand brand = brandRepository.findById(productUpdateRequest.getBrandId())
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found"));

        if (!product.getName().equalsIgnoreCase(productUpdateRequest.getName())
                && productRepository.existsByNameIgnoreCase(productUpdateRequest.getName())) {
            throw new BusinessException("Product name already exists");
        }

        product.updateProduct(productUpdateRequest.getName(), productUpdateRequest.getDescription(),
                productUpdateRequest.getPrice(), productUpdateRequest.getImgUrl(), category, brand);

        productRepository.save(product);

		return ProductMapper.toResponse(product);
    }

    @Transactional
    public ProductResponse adjustStock(Long id, ProductStockUpdateRequest request){

        if (id <= 0){
            throw new IllegalArgumentException("Product ID must be positive");
        }

        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        product.adjustStock(request.getQuantity());
        productRepository.save(product);

        return ProductMapper.toResponse(product);
    }

    @Transactional
    public void deactivateProduct(Long id){

        if (id <= 0){
            throw new IllegalArgumentException("Product ID must be positive");
        }

        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        product.deactivate();
        productRepository.save(product);
    }

    @Transactional
    public void activateProduct(Long id){

        if (id <= 0){
            throw new IllegalArgumentException("Product ID must be positive");
        }

        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        product.activate();
        productRepository.save(product);
    }

    @Transactional
    public void deleteOrDeactivateProduct(Long id){

        if (id <= 0){
            throw new IllegalArgumentException("Product ID must be positive");
        }

        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        if (orderItemRepository.existsByProductId(id)){
            product.deactivate();
            productRepository.save(product);
        } else {
            productRepository.delete(product);
        }
    }

    @Transactional(readOnly = true)
    public Page<ProductResponse> findInactiveProducts(Pageable pageable) {

        Page<Product> products =  productRepository.findByStatus(ProductStatus.INACTIVE.getCode(), pageable);
        return products.map(ProductMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public ProductResponse findInactiveProductById(Long id){

        if (id <= 0){
            throw new IllegalArgumentException("Product ID must be positive");
        }

        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        if (!ProductStatus.INACTIVE.equals(product.getStatus())){
            throw new ResourceNotFoundException("Inactive product not found");
        }

        return ProductMapper.toResponse(product);
    }

}
