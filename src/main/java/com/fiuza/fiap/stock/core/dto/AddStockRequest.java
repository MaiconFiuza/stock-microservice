package com.fiuza.fiap.stock.core.dto;

public record AddStockRequest(
        Long sku,
        Integer quantity
) {
}
