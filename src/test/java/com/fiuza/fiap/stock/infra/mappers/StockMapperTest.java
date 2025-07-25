package com.fiuza.fiap.stock.infra.mappers;

import com.fiuza.fiap.stock.core.entities.stock.Stock;
import com.fiuza.fiap.stock.infra.models.StockModel;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class StockMapperTest {

    @Test
    void stockToStockModel_shouldMapCorrectly() {
        UUID id = UUID.randomUUID();
        Long sku = 456L;
        Integer quantity = 20;
        Stock stock = new Stock(id, sku, quantity);

        StockModel stockModel = StockMapper.stockToStockModel(stock);

        assertThat(stockModel).isNotNull();
        assertThat(stockModel.getId()).isEqualTo(id);
        assertThat(stockModel.getProductSku()).isEqualTo(sku);
        assertThat(stockModel.getQuantity()).isEqualTo(quantity);
    }

    @Test
    void stockModelToStock_shouldMapCorrectly() {
        UUID id = UUID.randomUUID();
        Long sku = 789L;
        Integer quantity = 15;
        StockModel stockModel = new StockModel(id, sku, quantity);

        Stock stock = StockMapper.stockModelToStock(stockModel);

        assertThat(stock).isNotNull();
        assertThat(stock.getId()).isEqualTo(id);
        assertThat(stock.getProductSku()).isEqualTo(sku);
        assertThat(stock.getQuantity()).isEqualTo(quantity);
    }
}
