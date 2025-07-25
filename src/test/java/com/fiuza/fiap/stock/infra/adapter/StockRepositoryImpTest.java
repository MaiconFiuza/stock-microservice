package com.fiuza.fiap.stock.infra.adapter;


import com.fiuza.fiap.stock.core.entities.stock.Stock;
import com.fiuza.fiap.stock.core.exceptions.NotFoundException;
import com.fiuza.fiap.stock.infra.models.StockModel;

import com.fiuza.fiap.stock.infra.repository.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StockRepositoryImpTest {

    private StockRepository stockRepository;
    private StockRepositoryImp repositoryImp;

    @BeforeEach
    void setUp() {
        stockRepository = mock(StockRepository.class);
        repositoryImp = new StockRepositoryImp(stockRepository);
    }

    @Test
    void shouldReturnStockWhenSkuExists() {
        Long sku = 123L;
        StockModel model = new StockModel(UUID.randomUUID(), sku, 10);
        when(stockRepository.findByProductSku(sku)).thenReturn(Optional.of(model));

        Stock result = repositoryImp.findBySku(sku);

        assertEquals(sku, result.getProductSku());
        assertEquals(model.getQuantity(), result.getQuantity());
    }

    @Test
    void shouldThrowNotFoundWhenSkuDoesNotExist() {
        Long sku = 999L;
        when(stockRepository.findByProductSku(sku)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> repositoryImp.findBySku(sku));
    }

    @Test
    void shouldCreateStockSuccessfully() {
        Stock stock = new Stock(456L, 20);
        StockModel savedModel = new StockModel(UUID.randomUUID(), 456L, 20);

        when(stockRepository.save(any())).thenReturn(savedModel);

        Stock result = repositoryImp.create(stock);

        assertEquals(stock.getProductSku(), result.getProductSku());
        assertEquals(stock.getQuantity(), result.getQuantity());
    }

    @Test
    void shouldAddQuantityToExistingStock() {
        Long sku = 789L;
        StockModel existing = new StockModel(UUID.randomUUID(), sku, 5);
        when(stockRepository.findByProductSku(sku)).thenReturn(Optional.of(existing));

        Stock added = repositoryImp.add(new Stock(sku, 10), 10);

        assertEquals(15, added.getQuantity());
        assertEquals(sku, added.getProductSku());
    }

    @Test
    void shouldRemoveQuantityFromStockSuccessfully() {
        Long sku = 111L;
        UUID id = UUID.randomUUID();
        StockModel existing = new StockModel(id, sku, 20);
        StockModel updated = new StockModel(id, sku, 10);

        when(stockRepository.findByProductSku(sku)).thenReturn(Optional.of(existing));
        when(stockRepository.save(any())).thenReturn(updated);

        Stock result = repositoryImp.remove(sku, 10);

        assertEquals(10, result.getQuantity());
        assertEquals(sku, result.getProductSku());
    }

    @Test
    void shouldThrowIfRemovingMoreThanAvailable() {
        Long sku = 222L;
        StockModel existing = new StockModel(UUID.randomUUID(), sku, 5);
        when(stockRepository.findByProductSku(sku)).thenReturn(Optional.of(existing));

        assertThrows(RuntimeException.class, () -> repositoryImp.remove(sku, 10));
    }
}
