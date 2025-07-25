package com.fiuza.fiap.stock.core.entities.stock;

import java.util.UUID;

public class Stock {
    private UUID id;
    private Long productSku ;
    private Integer quantity;

    public Stock(UUID id, Long productSku, Integer quantity) {
        this.id = id;
        this.productSku = productSku;
        this.quantity = quantity;
    }

    public Stock(Long productSku, Integer quantity) {
        this.productSku = productSku;
        this.quantity = quantity;
    }

    public Stock(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getProductSku() {
        return productSku;
    }

    public void setProductSku(Long productSku) {
        this.productSku = productSku;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
