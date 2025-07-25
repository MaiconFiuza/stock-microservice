package com.fiuza.fiap.stock.infra.feign;

import com.fiuza.fiap.stock.core.entities.product.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-client", url = "${client.product.url}")
public interface FeignProductClient {

    @GetMapping("/product/{sku}")
    Product getProductBySku(@PathVariable("sku") Long sku);
}
