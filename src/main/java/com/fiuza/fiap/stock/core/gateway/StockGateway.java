package com.fiuza.fiap.stock.core.gateway;

import com.fiuza.fiap.stock.core.entities.stock.Stock;

public interface StockGateway {
    Stock findBySku(Long productSku);

    Stock create(Stock stock);

    Stock add(Stock stock, Integer quantity);

    Stock remove(Long sku, Integer quantity);
}
