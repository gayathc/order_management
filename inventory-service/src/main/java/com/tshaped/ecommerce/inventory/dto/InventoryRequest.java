package com.tshaped.ecommerce.inventory.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.antlr.v4.runtime.misc.NotNull;

public record InventoryRequest(
        @NotBlank
        String skuCode,

        @NotNull
        @Min(0)
        Integer quantity
) {
}
