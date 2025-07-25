package com.fiuza.fiap.stock.infra.config;

import com.fiuza.fiap.stock.core.gateway.ProductGateway;
import com.fiuza.fiap.stock.core.gateway.StockGateway;
import com.fiuza.fiap.stock.core.usecases.CreateProductIntStockUseCase;
import com.fiuza.fiap.stock.core.usecases.DropInStockUseCase;
import com.fiuza.fiap.stock.core.usecases.GetProductStockByIdUseCase;
import com.fiuza.fiap.stock.infra.adapter.StockRepositoryImp;
import com.fiuza.fiap.stock.infra.repository.StockRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockConfigs {

    @Bean
    public StockGateway stockGateway(StockRepository stockRepository) {
        return new StockRepositoryImp(stockRepository);
    }

    @Bean
    public CreateProductIntStockUseCase createProductIntStockUseCase(
            ProductGateway productGateway,
            StockGateway stockGateway
    ) {
        return new CreateProductIntStockUseCase(productGateway,stockGateway );
    }

    @Bean
    public GetProductStockByIdUseCase getProductStockByIdUseCase(
            StockGateway stockGateway
    ) {
        return new GetProductStockByIdUseCase(stockGateway);
    }

    @Bean
    public DropInStockUseCase dropInStockUseCase(StockGateway stockGateway) {
        return new DropInStockUseCase(stockGateway);
    }

}
