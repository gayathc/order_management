package com.tshaped.ecommerce.order.mapper;

import com.tshaped.ecommerce.order.dto.OrderLineItemRequest;
import com.tshaped.ecommerce.order.dto.OrderRequest;
import com.tshaped.ecommerce.order.dto.OrderResponse;
import com.tshaped.ecommerce.order.model.Order;
import com.tshaped.ecommerce.order.model.OrderLineItem;
import org.mapstruct.Mapper;

import java.util.concurrent.CompletableFuture;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity(OrderRequest request);

    OrderResponse toResponse(Order order);

    OrderLineItem toEntity(
            OrderLineItemRequest request);

}
