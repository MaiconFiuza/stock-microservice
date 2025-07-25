package com.fiuza.fiap.stock.infra.mappers;

import com.fiuza.fiap.stock.core.entities.stock.Stock;
import com.fiuza.fiap.stock.infra.models.StockModel;

public class StockMapper {

    public static StockModel stockToStockModel(Stock stock) {
        return new StockModel(
                stock.getId(),
                stock.getProductSku(),
                stock.getQuantity()
        );
    }

    public static Stock stockModelToStock(StockModel stockModel) {
        return new Stock(
                stockModel.getId(),
                stockModel.getProductSku(),
                stockModel.getQuantity()
        );
    }
}
