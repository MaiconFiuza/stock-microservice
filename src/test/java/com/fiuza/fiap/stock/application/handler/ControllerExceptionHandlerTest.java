package com.fiuza.fiap.stock.application.handler;


import com.fiuza.fiap.stock.core.dto.errors.BadRequestDto;
import com.fiuza.fiap.stock.core.dto.errors.InternalServerErrorDto;
import com.fiuza.fiap.stock.core.dto.errors.NotFoundExceptionDto;
import com.fiuza.fiap.stock.core.exceptions.BadRequest;
import com.fiuza.fiap.stock.core.exceptions.InternalServerError;
import com.fiuza.fiap.stock.core.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class ControllerExceptionHandlerTest {

    private ControllerExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new ControllerExceptionHandler();
    }

    @Test
    void shouldHandleNotFoundException() {
        String message = "Produto não encontrado";
        NotFoundException ex = new NotFoundException(message);

        ResponseEntity<NotFoundExceptionDto> response = handler.handlerNotFoundException(ex);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals(message, response.getBody().message());
        assertEquals(404, response.getBody().status());
    }

    @Test
    void shouldHandleInternalServerError() {
        String message = "Erro interno ao remover";
        InternalServerError ex = new InternalServerError(message);

        ResponseEntity<InternalServerErrorDto> response = handler.handlerInternalServerErrorException(ex);

        assertEquals(500, response.getStatusCodeValue());
        assertEquals(message, response.getBody().message());
        assertEquals(500, response.getBody().status());
    }

    @Test
    void shouldHandleBadRequest() {
        String message = "Requisição inválida";
        BadRequest ex = new BadRequest(message);

        ResponseEntity<BadRequestDto> response = handler.handlerBadRequestException(ex);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals(message, response.getBody().message());
        assertEquals(400, response.getBody().status());
    }
}
