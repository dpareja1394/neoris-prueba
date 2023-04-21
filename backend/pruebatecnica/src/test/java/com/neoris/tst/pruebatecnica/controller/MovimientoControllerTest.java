package com.neoris.tst.pruebatecnica.controller;

import com.neoris.tst.pruebatecnica.exception.CuentaException;
import com.neoris.tst.pruebatecnica.exception.MovimientoException;
import com.neoris.tst.pruebatecnica.exception.TipoCuentaException;
import com.neoris.tst.pruebatecnica.exception.TipoMovimientoException;
import com.neoris.tst.pruebatecnica.response.RealizarMovimientoResponse;
import com.neoris.tst.pruebatecnica.service.MovimientoService;
import com.neoris.tst.pruebatecnica.utility.controller.MovimientoRequestConstante;
import com.neoris.tst.pruebatecnica.utility.controller.MovimientoResponseConstante;
import com.neoris.tst.pruebatecnica.utility.validations.RealizarMovimientoValidate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class MovimientoControllerTest {

    @Autowired
    private MovimientoController movimientoController;

    @MockBean
    private MovimientoService movimientoService;

    @Test
    void realizarMovimiento() throws CuentaException, TipoCuentaException, TipoMovimientoException, MovimientoException {
        when(movimientoService.realizarMovimiento(any())).thenReturn(MovimientoResponseConstante.REALIZAR_MOVIMIENTO_RESPONSE);
        ResponseEntity<RealizarMovimientoResponse> response =
                movimientoController.realizarMovimiento(MovimientoRequestConstante.REALIZAR_MOVIMIENTO_REQUEST_OK);
        verify(movimientoService).realizarMovimiento(eq(MovimientoRequestConstante.REALIZAR_MOVIMIENTO_REQUEST_OK));
        assertTrue(response.getStatusCode().value() == HttpStatus.CREATED.value());
        assertEquals(response.getBody().getNumeroCuenta(), MovimientoResponseConstante.NUMERO_CUENTA);
    }

    @Test
    void realizarMovimiento_datosInvalidos_excepcionEsperada() {
        try {
            movimientoController.realizarMovimiento(MovimientoRequestConstante.REALIZAR_MOVIMIENTO_REQUEST_NUMERO_CUENTA_NULL);
        } catch (Exception e) {
            assertEquals(RealizarMovimientoValidate.NUMERO_CUENTA_REQUIRED, e.getMessage());
        }
    }

}