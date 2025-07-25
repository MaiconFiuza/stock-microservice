package com.fiuza.fiap.stock.core.gateway;

import com.fiuza.fiap.stock.core.entities.product.Product;

public interface ProductGateway {
    Product findBySku(Long sku);
}
