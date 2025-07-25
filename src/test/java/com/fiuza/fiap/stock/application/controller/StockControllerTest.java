package com.fiuza.fiap.stock.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiuza.fiap.stock.core.dto.AddStockRequest;
import com.fiuza.fiap.stock.core.entities.stock.Stock;
import com.fiuza.fiap.stock.core.usecases.CreateProductIntStockUseCase;
import com.fiuza.fiap.stock.core.usecases.DropInStockUseCase;
import com.fiuza.fiap.stock.core.usecases.GetProductStockByIdUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StockController.class)
class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateProductIntStockUseCase createProductIntStockUseCase;

    @MockBean
    private GetProductStockByIdUseCase getProductStockByIdUseCase;

    @MockBean
    private DropInStockUseCase dropInStockUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void create_shouldReturn201AndCreatedStock() throws Exception {
        // Arrange
        AddStockRequest request = new AddStockRequest(123L, 10);
        Stock stock = new Stock(UUID.randomUUID(), 123L, 10);

        when(createProductIntStockUseCase.execute(123L, 10)).thenReturn(stock);

        // Act & Assert
        mockMvc.perform(post("/stock")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.productSku").value(123L))
                .andExpect(jsonPath("$.quantity").value(10));
    }

    @Test
    void get_shouldReturn200AndStock() throws Exception {
        // Arrange
        Long sku = 456L;
        Stock stock = new Stock(UUID.randomUUID(), sku, 5);

        when(getProductStockByIdUseCase.execute(sku)).thenReturn(stock);

        // Act & Assert
        mockMvc.perform(get("/stock/{sku}", sku))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productSku").value(sku))
                .andExpect(jsonPath("$.quantity").value(5));
    }

    @Test
    void update_shouldReturn200AndUpdatedStock() throws Exception {
        // Arrange
        Long sku = 789L;
        Integer quantity = 3;
        Stock updatedStock = new Stock(UUID.randomUUID(), sku, 7);

        when(dropInStockUseCase.execute(sku, quantity)).thenReturn(updatedStock);

        // Act & Assert
        mockMvc.perform(put("/stock/remove/{sku}/{quantity}", sku, quantity))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productSku").value(sku))
                .andExpect(jsonPath("$.quantity").value(7));
    }
}
