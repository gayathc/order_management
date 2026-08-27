package com.tshaped.ecommerce.inventory.dto;

import lombok.Builder;

@Builder
public record InventoryResponse(
    Long id,
    String skuCode,
    Integer quantity)
{}
