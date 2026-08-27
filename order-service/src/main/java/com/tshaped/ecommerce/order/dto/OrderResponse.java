package com.tshaped.ecommerce.order.dto;

import lombok.Builder;

@Builder
public record OrderResponse(
        String orderNumber
)
{}
