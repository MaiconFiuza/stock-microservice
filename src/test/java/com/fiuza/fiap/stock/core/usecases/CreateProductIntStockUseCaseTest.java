package com.fiuza.fiap.stock.core.usecases;


import com.fiuza.fiap.stock.core.entities.product.Product;
import com.fiuza.fiap.stock.core.entities.stock.Stock;
import com.fiuza.fiap.stock.core.exceptions.NotFoundException;
import com.fiuza.fiap.stock.core.gateway.ProductGateway;
import com.fiuza.fiap.stock.core.gateway.StockGateway;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateProductIntStockUseCaseTest {

    @Mock
    private ProductGateway productGateway;

    @Mock
    private StockGateway stockGateway;

    @InjectMocks
    private CreateProductIntStockUseCase useCase;

    private BigDecimal price;
    private int quantity;
    private Long productId;
    private Product product;
    private Stock savedStock;

    @BeforeEach
    void setUp() {
        price = BigDecimal.valueOf(12.50);
        quantity = 10;
        productId = 1L;

        product = new Product(productId, "Product Name", price);
        savedStock = new Stock(UUID.randomUUID(), productId, quantity);
    }

    @Test
    void execute_shouldReturnSavedStock_whenProductExists() {
        when(productGateway.findBySku(productId)).thenReturn(product);
        when(stockGateway.create(any(Stock.class))).thenReturn(savedStock);

        Stock result = useCase.execute(productId, quantity);

        assertThat(result).isNotNull();
        assertThat(result.getProductSku()).isEqualTo(productId);
        assertThat(result.getQuantity()).isEqualTo(quantity);

        verify(productGateway).findBySku(productId);
        verify(stockGateway).create(any(Stock.class));
    }

    @Test
    void execute_shouldThrowRuntimeException_whenProductIsNull() {
        when(productGateway.findBySku(productId)).thenReturn(null);

        assertThatThrownBy(() -> useCase.execute(productId, quantity))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Produto não encontrado");

        verify(productGateway).findBySku(productId);
        verifyNoInteractions(stockGateway);
    }
}

