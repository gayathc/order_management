package com.tshaped.ecommerce.product.repository;

import com.tshaped.ecommerce.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsBySkuCode(String skuCode);
    Optional<Product> findBySkuCode(String skuCode);
}
