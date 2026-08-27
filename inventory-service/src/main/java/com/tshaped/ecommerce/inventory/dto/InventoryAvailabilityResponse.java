package com.tshaped.ecommerce.inventory.dto;

import lombok.Builder;

@Builder
public record InventoryAvailabilityResponse(
        String skuCode,
        boolean isInStock) {
}
