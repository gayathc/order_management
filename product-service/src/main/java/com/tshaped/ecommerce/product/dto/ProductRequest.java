package com.tshaped.ecommerce.product.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductRequest(
        @NotBlank(message = "SKU code is required")
        String skuCode,

        @NotBlank(message = "Product name can not be blank")
        String name,

        String description,

        @NotBlank(message = "Price must be added")
        @DecimalMin(value = "0.01", message = "Price must be greater than zero")
        BigDecimal price)
 {}
