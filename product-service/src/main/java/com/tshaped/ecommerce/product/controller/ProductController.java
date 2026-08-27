package com.tshaped.ecommerce.product.controller;

import com.tshaped.ecommerce.product.dto.ProductRequest;
import com.tshaped.ecommerce.product.dto.ProductResponse;
import com.tshaped.ecommerce.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    @PostMapping

    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest){
        return ResponseEntity.ok().body(productService.createProduct(productRequest));
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(
            @PathVariable Long id){
        return productService.getProductById(id);
    }

    @GetMapping("/sku/{sku}")
    public ProductResponse getProductBySkuCode(
            @PathVariable String sku){
        return productService.getProductBySkuCode(sku);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(
            @PathVariable Long id,
            @Valid
            @RequestBody ProductRequest request){
        return productService.updateProduct(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(
            @PathVariable Long id){
        productService.deleteProduct(id);
    }
}
