package com.fiuza.fiap.stock.infra.feign;

import com.fiuza.fiap.stock.core.entities.product.Product;
import com.fiuza.fiap.stock.core.gateway.ProductGateway;
import org.springframework.stereotype.Component;

@Component
public class ProductClientAdapter implements ProductGateway {

    private final FeignProductClient feignProductClient;

    public ProductClientAdapter(FeignProductClient feignProductClient) {
        this.feignProductClient = feignProductClient;
    }

    @Override
    public Product findBySku(Long sku) {
        var dto = feignProductClient.getProductBySku(sku);
        return new Product(dto.getId(), dto.getName(), dto.getPrice());// nao da pra retonrar direto ???????
    }
}
