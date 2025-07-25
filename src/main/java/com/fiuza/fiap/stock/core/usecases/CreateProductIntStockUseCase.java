package com.fiuza.fiap.stock.core.usecases;

import com.fiuza.fiap.stock.core.entities.stock.Stock;
import com.fiuza.fiap.stock.core.exceptions.InternalServerError;
import com.fiuza.fiap.stock.core.exceptions.NotFoundException;
import com.fiuza.fiap.stock.core.gateway.ProductGateway;
import com.fiuza.fiap.stock.core.gateway.StockGateway;

public class CreateProductIntStockUseCase {
    private final ProductGateway productGateway;
    private final StockGateway stockGateway;

    public CreateProductIntStockUseCase(ProductGateway productGateway, StockGateway stockGateway) {
        this.productGateway = productGateway;
        this.stockGateway = stockGateway;
    }

    public Stock execute(Long sku, int quantity) {
        try {
            var product = productGateway.findBySku(sku);

            if(product == null) {
                throw new NotFoundException("Produto não encontrado");
            }

            var newProductAtStock = new Stock(product.getId(), quantity);
            Stock savedStock = stockGateway.create(newProductAtStock);
            return savedStock;

        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
         }catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }
}
