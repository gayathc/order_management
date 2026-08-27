package com.tshaped.ecommerce.inventory.mapper;

import com.tshaped.ecommerce.inventory.dto.InventoryRequest;
import com.tshaped.ecommerce.inventory.dto.InventoryResponse;
import com.tshaped.ecommerce.inventory.model.Inventory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
    Inventory toEntity(InventoryRequest request);
    InventoryResponse toResponse(Inventory inventory);
}