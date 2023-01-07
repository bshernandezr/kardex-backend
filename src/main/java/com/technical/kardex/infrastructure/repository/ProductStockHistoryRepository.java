package com.technical.kardex.infrastructure.repository;

import com.technical.kardex.infrastructure.entities.ProductStockHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStockHistoryRepository extends JpaRepository<ProductStockHistory, Long> {
}
