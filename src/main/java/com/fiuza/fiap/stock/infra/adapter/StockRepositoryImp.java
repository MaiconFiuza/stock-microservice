package com.fiuza.fiap.stock.infra.adapter;

import com.fiuza.fiap.stock.core.entities.stock.Stock;
import com.fiuza.fiap.stock.core.exceptions.BadRequest;
import com.fiuza.fiap.stock.core.exceptions.NotFoundException;
import com.fiuza.fiap.stock.core.gateway.StockGateway;
import com.fiuza.fiap.stock.infra.mappers.StockMapper;
import com.fiuza.fiap.stock.infra.models.StockModel;
import com.fiuza.fiap.stock.infra.repository.StockRepository;

public class StockRepositoryImp implements StockGateway {

    private final StockRepository stockRepository;

    public StockRepositoryImp(StockRepository stockRepository) {this.stockRepository = stockRepository;}

    @Override
    public Stock findBySku(Long productSku) {
        StockModel stockModel = stockRepository
                .findByProductSku(productSku).orElseThrow(()-> new NotFoundException("Produto não existe no Stock") );

        return StockMapper.stockModelToStock(stockModel) ;
    }

    @Override
    public Stock create(Stock stock) {
        StockModel stockModel = StockMapper.stockToStockModel(stock);
        var stockSaved = stockRepository.save(stockModel);
        return StockMapper.stockModelToStock(stockSaved);
    }

    @Override
    public Stock add(Stock stock, Integer quantity) {
        var oldStock = stockRepository.findByProductSku(stock.getProductSku())
                .orElseThrow(() ->  new NotFoundException("Produto não existe no Stock"));

        return new Stock(
                oldStock.getId(),oldStock.getProductSku(), oldStock.getQuantity() + quantity );
    }

    @Override
    public Stock remove(Long sku, Integer quantity) {
        var oldStock = stockRepository
                .findByProductSku(sku).orElseThrow(()-> new NotFoundException("Produto não existe no Stock") );

        if(oldStock.getQuantity() < quantity) {
            throw new BadRequest("Quantidade desejada não existe no Stock");
        }

        var newQuantity = oldStock.getQuantity() - quantity;
        var stock = new Stock(
                oldStock.getId(),oldStock.getProductSku(),newQuantity);

        var StockModel = StockMapper.stockToStockModel(stock);

        var updatedStock = stockRepository.save(StockModel);

        return  StockMapper.stockModelToStock(updatedStock);
    }
}
