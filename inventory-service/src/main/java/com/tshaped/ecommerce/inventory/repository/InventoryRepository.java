package com.tshaped.ecommerce.inventory.repository;

import com.tshaped.ecommerce.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findBySkuCode(String skuCode);

    boolean existsBySkuCode(String skuCode);

    Optional<Inventory> findBySkuCodeIn(List<String> skuCodeList);
}
