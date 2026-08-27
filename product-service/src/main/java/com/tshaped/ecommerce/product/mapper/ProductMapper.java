package com.tshaped.ecommerce.product.mapper;

import com.tshaped.ecommerce.product.dto.ProductRequest;
import com.tshaped.ecommerce.product.dto.ProductResponse;
import com.tshaped.ecommerce.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    // Request DTO → Entity
    @Mapping(target = "id", ignore = true)
    //@Mapping(target = "createdAt", expression = "java(java.time.Instant.now())")
    Product toEntity(ProductRequest request);

    // Entity → Response DTO
    ProductResponse toResponse(Product product);
}
