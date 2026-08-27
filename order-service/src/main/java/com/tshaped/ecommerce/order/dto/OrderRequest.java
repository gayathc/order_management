package com.tshaped.ecommerce.order.dto;

import java.util.List;

public record OrderRequest (
    List<OrderLineItemRequest> orderLineItems)
{}
