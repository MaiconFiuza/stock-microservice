package com.fiuza.fiap.stock.core.usecases;

import com.fiuza.fiap.stock.core.entities.stock.Stock;
import com.fiuza.fiap.stock.core.exceptions.BadRequest;
import com.fiuza.fiap.stock.core.exceptions.InternalServerError;
import com.fiuza.fiap.stock.core.exceptions.NotFoundException;
import com.fiuza.fiap.stock.core.gateway.StockGateway;

public class DropInStockUseCase {
    private final StockGateway stockGateway;

    public DropInStockUseCase(StockGateway stockGateway) {
        this.stockGateway =stockGateway;
    }

    public Stock execute(Long sku, Integer quantity) {
        try {
            return stockGateway.remove(sku, quantity);
        }catch (BadRequest e) {
            throw new BadRequest(e.getMessage());
        }
        catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        catch (Exception e) {
            throw new InternalServerError("Aconteceu um erro inesperado. Por favor tente novamente");
        }
    }
}
