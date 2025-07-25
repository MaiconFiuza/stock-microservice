package com.fiuza.fiap.stock.infra.repository;

import com.fiuza.fiap.stock.infra.models.StockModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StockRepository extends JpaRepository<StockModel, UUID> {
    Optional<StockModel> findByProductSku(Long productSku);
}
