package com.tshaped.ecommerce.product.service;

import com.tshaped.ecommerce.product.dto.ProductRequest;
import com.tshaped.ecommerce.product.dto.ProductResponse;
import com.tshaped.ecommerce.product.exceptions.ProductNotFoundException;
import com.tshaped.ecommerce.product.model.Product;
import com.tshaped.ecommerce.product.repository.ProductRepository;
import com.tshaped.ecommerce.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductResponse createProduct(ProductRequest productRequest){
        Product product = productMapper.toEntity(productRequest);

//        Product product = Product.builder()
//                .name(productRequest.name())
//                .description(productRequest.description())
//                .price(productRequest.price())
//                .build();
        productRepository.save(product);

        log.info("Product {} Saved.", product.getName());

        return ProductResponse.builder()
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .build();
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    @Transactional(readOnly = true)
    public ProductResponse getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found : " + id));
        return productMapper.toResponse(product);
    }

    @Transactional(readOnly = true)
    public ProductResponse getProductBySkuCode(String skuCode) {
        Product product = productRepository.findBySkuCode(skuCode)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "SKU not found : " + skuCode));
        return productMapper.toResponse(product);
    }

    public ProductResponse updateProduct(Long id,
                                         ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found"));
        product.setSkuCode(request.skuCode());
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setUpdatedAt(LocalDateTime.now());
        return productMapper.toResponse(product);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found"));
        productRepository.delete(product);
    }
}
