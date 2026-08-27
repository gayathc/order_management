package com.tshaped.ecommerce.inventory.controller;

import com.tshaped.ecommerce.inventory.dto.InventoryAvailabilityResponse;
import com.tshaped.ecommerce.inventory.dto.InventoryRequest;
import com.tshaped.ecommerce.inventory.dto.InventoryResponse;
import com.tshaped.ecommerce.inventory.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryResponse create(
            @Valid
            @RequestBody InventoryRequest request) {

        return inventoryService.createInventory(request);
    }

    @GetMapping
    public List<InventoryResponse> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @GetMapping("/{skuCode}")
    public InventoryResponse getBySkuCode(
            @PathVariable String skuCode) {
        return inventoryService.getInventory(skuCode);
    }

    @GetMapping("/check")
    public InventoryAvailabilityResponse checkAvailability(
            @RequestParam String skuCode,
            @RequestParam Integer quantity) {

        return inventoryService.checkAvailability(
                skuCode,
                quantity);
    }

//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<InventoryResponse> isInStock(@RequestParam("skuCode") List<String> skuCodeList){
//        log.info("SKU LIST *************** : " + skuCodeList.toString());
//        return inventoryService.isInStock(skuCodeList);
//    }
}
