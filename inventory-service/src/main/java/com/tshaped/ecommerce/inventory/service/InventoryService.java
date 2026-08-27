package com.tshaped.ecommerce.inventory.service;

import com.tshaped.ecommerce.inventory.dto.InventoryAvailabilityResponse;
import com.tshaped.ecommerce.inventory.dto.InventoryRequest;
import com.tshaped.ecommerce.inventory.dto.InventoryResponse;
import com.tshaped.ecommerce.inventory.exceptions.DuplicateInventoryException;
import com.tshaped.ecommerce.inventory.exceptions.InventoryNotFoundException;
import com.tshaped.ecommerce.inventory.mapper.InventoryMapper;
import com.tshaped.ecommerce.inventory.model.Inventory;
import com.tshaped.ecommerce.inventory.repository.InventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final InventoryMapper mapper;

    //@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
//    public List<InventoryResponse> isInStock(List<String> skuCodeList){
//        return inventoryRepository.findBySkuCodeIn(skuCodeList)
//                .stream()
//                .map(inventory ->
//                        InventoryAvailabilityResponse.builder()
//                            .skuCode(inventory.getSkuCode())
//                            .isInStock(inventory.getQuantity() > 0)
//                            .build()
//                ).toList();
//    }

    public InventoryResponse createInventory(
            InventoryRequest request) {

        if (inventoryRepository.existsBySkuCode(request.skuCode())) {
            throw new DuplicateInventoryException(
                    "SKU already exists.");
        }

        Inventory inventory =
                mapper.toEntity(request);

        inventoryRepository.save(inventory);

        log.info("Inventory created for {}",
                inventory.getSkuCode());

        return mapper.toResponse(inventory);
    }

    @Transactional(readOnly = true)
    public List<InventoryResponse> getAllInventory() {

        return inventoryRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public InventoryResponse getInventory(String skuCode) {

        Inventory inventory =
                inventoryRepository.findBySkuCode(skuCode)
                        .orElseThrow(() ->
                                new InventoryNotFoundException(
                                        "Inventory not found"));
        return mapper.toResponse(inventory);
    }

    @Transactional(readOnly = true)
    public InventoryAvailabilityResponse checkAvailability(
            String skuCode,
            Integer quantity) {

        Inventory inventory =
                inventoryRepository.findBySkuCode(skuCode)
                        .orElseThrow(() ->
                                new InventoryNotFoundException(
                                        "Inventory not found"));
        boolean available =
                inventory.getQuantity() >= quantity;
        return new InventoryAvailabilityResponse(
                skuCode, available
        );
    }
}