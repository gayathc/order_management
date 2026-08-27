package com.tshaped.ecommerce.order.service;

import com.tshaped.ecommerce.order.client.InventoryClient;
import com.tshaped.ecommerce.order.dto.InventoryAvailabilityResponse;
import com.tshaped.ecommerce.order.dto.OrderResponse;
import com.tshaped.ecommerce.order.dto.OrderLineItemRequest;
import com.tshaped.ecommerce.order.dto.OrderRequest;
import com.tshaped.ecommerce.order.mapper.OrderMapper;
import com.tshaped.ecommerce.order.model.Order;
import com.tshaped.ecommerce.order.model.OrderLineItem;
import com.tshaped.ecommerce.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    private final InventoryClient inventoryClient;

    public OrderResponse placeOrder(OrderRequest request) {

        log.info("Creating order...");

        Order order = mapper.toEntity(request);
        order.setOrderNumber(UUID.randomUUID().toString());

        validateInventory(request);

        Order savedOrder = orderRepository.save(order);

        log.info("Order {} created successfully.", savedOrder.getOrderNumber());

        return mapper.toResponse(savedOrder);

    }

    private void validateInventory(OrderRequest request) {

        for (OrderLineItemRequest item : request.orderLineItems()) {

            InventoryAvailabilityResponse response =
                    inventoryClient.checkAvailability(
                            item.skuCode(),
                            item.quantity());

            if (!response.isInStock()) {
                throw new IllegalStateException(
                        "Insufficient inventory for SKU: " + item.skuCode());
            }
        }
    }



//    private final WebClient.Builder webClientBuilder;

//    public String placeOrder(OrderRequest orderRequest){
//        Order order = new Order();
//        order.setOrderNumber(UUID.randomUUID().toString());
//
//        List<OrderLineItem> orderLineItems = orderRequest.orderLineItems()
//                .stream()
//                .map(this::mapToDto)
//                .toList();
//        order.setOrderLineItems(orderLineItems);
//        //Call inventory service, and place order if product is available in the stock
//        List<String> skuCodes = order.getOrderLineItems().stream()
//                .map(OrderLineItem::getSkuCode)
//                .toList();
//        log.info("SKU CODES >>>>>>>>>>>>> " + skuCodes);
//        OrderResponse[] inventoryResponseArray;
//        inventoryResponseArray = webClientBuilder.build().get()
//                .uri("http://inventory-service/api/inventory",
//                        uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
//                        .retrieve()
//                        .bodyToMono(OrderResponse[].class)
//                        .block();
//        assert inventoryResponseArray != null;
//        log.info("Inventory validation request URL : " + Arrays.toString(inventoryResponseArray));
//        boolean allProductsInStock = Arrays.stream(inventoryResponseArray).allMatch(OrderResponse::isInStock);
//
//        if (allProductsInStock) {
//            orderRepository.save(order);
//            return "Order Placed Successfully.";
//        }else{
//            throw new IllegalArgumentException("Product not in stock, Please try again later.");
//        }
//    }
//
//    private OrderLineItem mapToDto(OrderLineItemRequest orderLineItemRequest) {
//        OrderLineItem orderLineItem = new OrderLineItem();
//
//        orderLineItem.setPrice(orderLineItemRequest.price());
//        orderLineItem.setQuantity(orderLineItemRequest.quantity());
//        orderLineItem.setSkuCode(orderLineItemRequest.skuCode());
//        return orderLineItem;
//    }
}
