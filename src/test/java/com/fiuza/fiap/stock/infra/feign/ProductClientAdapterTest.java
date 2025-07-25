package com.fiuza.fiap.stock.infra.feign;


import com.fiuza.fiap.stock.core.entities.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductClientAdapterTest {

    private FeignProductClient feignProductClient;
    private ProductClientAdapter adapter;

    @BeforeEach
    void setUp() {
        feignProductClient = mock(FeignProductClient.class);
        adapter = new ProductClientAdapter(feignProductClient);
    }

    @Test
    void shouldReturnProductWhenFeignReturnsDto() {
        Long productId = 1L;
        Long sku = 100L;
        String name = "Produto Teste";
        BigDecimal price = new BigDecimal("29.90");

        Product dto = new Product(productId, name, price);
        when(feignProductClient.getProductBySku(sku)).thenReturn(dto);

        Product result = adapter.findBySku(sku);

        assertNotNull(result);
        assertEquals(productId, result.getId());
        assertEquals(name, result.getName());
        assertEquals(price, result.getPrice());
    }
}
