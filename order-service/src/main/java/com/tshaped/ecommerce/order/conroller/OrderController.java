package com.tshaped.ecommerce.order.conroller;

import com.tshaped.ecommerce.order.dto.OrderRequest;
import com.tshaped.ecommerce.order.dto.OrderResponse;
import com.tshaped.ecommerce.order.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Validated
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
//    @TimeLimiter(name="inventory")
//    @Retry(name="inventory")
//    public CompletableFuture<OrderResponse> placeOrder(
    public OrderResponse placeOrder(
            @Valid @RequestBody OrderRequest request) {
//      CompletableFuture.supplyAsync(() ->
        return orderService.placeOrder(request);
    }



//    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest){
//        return CompletableFuture.supplyAsync(()-> orderService.placeOrder(orderRequest));
//    }

//    public CompletableFuture<OrderResponse> fallbackMethod(
//            OrderRequest orderRequest,
//            Throwable throwable) {
//
//        return CompletableFuture.supplyAsync(() ->
//                new OrderResponse("Ops!! Something went wrong. Please retry after sometimes.")
//        );
//    }
}
