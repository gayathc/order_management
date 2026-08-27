package com.tshaped.ecommerce.order.client;

import com.tshaped.ecommerce.order.dto.InventoryAvailabilityResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class InventoryClient {

    private final WebClient.Builder webClient;

    public InventoryAvailabilityResponse checkAvailability(
            String skuCode,
            Integer quantity) {

        return webClient.build()
                .get()
                .uri("http://inventory-service/api/inventory/check",
                        uriBuilder -> uriBuilder
                                .queryParam("skuCode", skuCode)
                                .queryParam("quantity", quantity)
                                .build())
                .retrieve()
                .bodyToMono(InventoryAvailabilityResponse.class)
                .block();
    }
}
