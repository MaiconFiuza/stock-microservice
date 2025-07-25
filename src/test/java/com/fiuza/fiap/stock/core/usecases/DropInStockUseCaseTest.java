package com.fiuza.fiap.stock.core.usecases;

import com.fiuza.fiap.stock.core.entities.stock.Stock;
import com.fiuza.fiap.stock.core.exceptions.InternalServerError;
import com.fiuza.fiap.stock.core.gateway.StockGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DropInStockUseCaseTest {

    @Mock
    private StockGateway stockGateway;

    @InjectMocks
    private DropInStockUseCase dropInStockUseCase;

    private Long sku;
    private Integer quantity;
    private Stock stock;

    @BeforeEach
    void setUp() {
        sku = 123L;
        quantity = 5;
        stock = new Stock(UUID.randomUUID(), sku, 10);
    }

    @Test
    void execute_shouldReturnStock_whenRemoveIsSuccessful() {
        when(stockGateway.remove(sku, quantity)).thenReturn(stock);

        Stock result = dropInStockUseCase.execute(sku, quantity);

        assertThat(result).isNotNull();
        assertThat(result.getProductSku()).isEqualTo(sku);
        assertThat(result.getQuantity()).isEqualTo(stock.getQuantity());

        verify(stockGateway).remove(sku, quantity);
    }

    @Test
    void execute_shouldThrowInternalServerError_whenGatewayThrowsException() {
        when(stockGateway.remove(sku, quantity))
                .thenThrow(new InternalServerError("Aconteceu um erro inesperado. Por favor tente novamente"));

        assertThatThrownBy(() -> dropInStockUseCase.execute(sku, quantity))
                .isInstanceOf(InternalServerError.class)
                .hasMessage("Aconteceu um erro inesperado. Por favor tente novamente");

        verify(stockGateway).remove(sku, quantity);
    }
}