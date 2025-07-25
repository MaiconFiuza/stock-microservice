package com.fiuza.fiap.stock.core.usecases;

import com.fiuza.fiap.stock.core.entities.stock.Stock;
import com.fiuza.fiap.stock.core.exceptions.InternalServerError;
import com.fiuza.fiap.stock.core.exceptions.NotFoundException;
import com.fiuza.fiap.stock.core.gateway.StockGateway;

public class GetProductStockByIdUseCase {

    private final StockGateway stockGateway;

    public GetProductStockByIdUseCase(
       StockGateway stockGateway
    ) {
        this.stockGateway = stockGateway;
    }

    public Stock execute(Long sku) {
        try {
            return stockGateway.findBySku(sku);
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerError("Aconteceu um erro inesperado. Por favor tente novamente");
        }

    }

}
