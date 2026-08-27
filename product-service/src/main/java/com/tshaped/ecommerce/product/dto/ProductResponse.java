package com.tshaped.ecommerce.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
public record ProductResponse(
    Long id,
    String name,
    String description,
    BigDecimal price)
{}
