package com.tshaped.ecommerce.order.dto;

import java.math.BigDecimal;

public record OrderLineItemResponse(
        Long id,
        String skuCode,
        BigDecimal price,
        Integer quantity
) {
}
