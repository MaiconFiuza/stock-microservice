package com.fiuza.fiap.stock.application.controller;

import com.fiuza.fiap.stock.core.dto.AddStockRequest;
import com.fiuza.fiap.stock.core.entities.stock.Stock;
import com.fiuza.fiap.stock.core.usecases.CreateProductIntStockUseCase;
import com.fiuza.fiap.stock.core.usecases.DropInStockUseCase;
import com.fiuza.fiap.stock.core.usecases.GetProductStockByIdUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("stock")
public class StockController {
    private static  final Logger logger = LoggerFactory.getLogger(StockController.class);

    private CreateProductIntStockUseCase createProductIntStockUseCase;
    private GetProductStockByIdUseCase getProductStockByIdUseCase;
    private DropInStockUseCase dropInStockUseCase;

    public StockController(
            CreateProductIntStockUseCase createProductIntStockUseCase,
            GetProductStockByIdUseCase getProductStockByIdUseCase,
            DropInStockUseCase dropInStockUseCase
    ) {
        this.createProductIntStockUseCase = createProductIntStockUseCase;
        this.getProductStockByIdUseCase = getProductStockByIdUseCase;
        this.dropInStockUseCase = dropInStockUseCase;

    }

    @Operation(
            description = "Criar um produto no stock",
            summary = "Endpoint responsável por criar um produto no stock",
            responses = {
                    @ApiResponse(description = "CREATED", responseCode = "201")
            }
    )
    @PostMapping
    public ResponseEntity<Stock> create(@RequestBody AddStockRequest request) {
        logger.info("POST");
        Stock result = createProductIntStockUseCase.execute(request.sku(), request.quantity());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Operation(
            description = "Busca um produto no stock",
            summary = "Endpoint responsável por buscar um produto no stock",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200")
            }
    )
    @GetMapping("/{sku}")
    public ResponseEntity<Stock> get(@PathVariable Long sku) {
        logger.info("GET /{}", sku);
        Stock result = getProductStockByIdUseCase.execute(sku);
        return ResponseEntity.ok(result);
    }


    @Operation(
            description = "Atualiza o stock",
            summary = "Endpoint responsável por atualizar produto no stock",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200")
            }
    )
    @PutMapping("/remove/{sku}/{quantity}")
    public ResponseEntity<Stock> update(@PathVariable Long sku, @PathVariable Integer quantity ) {
        logger.info("UPDATE /{}", sku);
        Stock result = dropInStockUseCase.execute(sku, quantity);
        return ResponseEntity.ok(result);
    }

}
