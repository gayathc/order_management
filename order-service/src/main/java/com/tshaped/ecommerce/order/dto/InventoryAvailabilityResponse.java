package com.tshaped.ecommerce.order.dto;

import lombok.Builder;

@Builder
public record InventoryAvailabilityResponse(
        String skuCode,
        boolean isInStock) {
}
