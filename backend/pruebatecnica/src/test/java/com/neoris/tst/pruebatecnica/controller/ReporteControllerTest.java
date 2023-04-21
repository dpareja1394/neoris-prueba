package com.neoris.tst.pruebatecnica.controller;

import com.neoris.tst.pruebatecnica.exception.ClienteException;
import com.neoris.tst.pruebatecnica.exception.CuentaException;
import com.neoris.tst.pruebatecnica.exception.PersonaException;
import com.neoris.tst.pruebatecnica.exception.TipoCuentaException;
import com.neoris.tst.pruebatecnica.response.MovimientoPorFechaPorCuentaResponse;
import com.neoris.tst.pruebatecnica.response.MovimientoPorFechaPorUsuarioResponse;
import com.neoris.tst.pruebatecnica.service.MovimientoService;
import com.neoris.tst.pruebatecnica.utility.controller.ReporteRequestConstante;
import com.neoris.tst.pruebatecnica.utility.controller.ReporteResponseConstante;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ReporteControllerTest {

    @Autowired
    private ReporteController reporteController;

    @MockBean
    private MovimientoService movimientoService;

    @Test
    void movimientosPorClienteYFechas() throws PersonaException, ClienteException {
        when(movimientoService.buscarMovimientosEnLasCuentasDeUnCliente(anyString(), any(), any()))
                .thenReturn(ReporteResponseConstante.MOVIMIENTOS_POR_FECHA_POR_USUARIO);

        ResponseEntity<List<MovimientoPorFechaPorUsuarioResponse>> response =
                reporteController.movimientosPorClienteYFechas(
                        ReporteRequestConstante.IDENTIFICACION,
                        ReporteRequestConstante.DESDE,
                        ReporteRequestConstante.HASTA
                );

        verify(movimientoService).buscarMovimientosEnLasCuentasDeUnCliente(
                (eq(ReporteRequestConstante.IDENTIFICACION)),
                (eq(ReporteRequestConstante.DESDE)),
                (eq(ReporteRequestConstante.HASTA)))
                ;

        assertTrue(response.getStatusCode().value() == HttpStatus.OK.value());
        assertEquals(response.getBody().size(), 2);
        assertEquals(response.getBody().get(0).getSaldoInicial(), ReporteResponseConstante.SALDO_INICIAL);
    }

    @Test
    void movimientosPorCuentaTipoCuentaYFechas() throws CuentaException, TipoCuentaException {
        when(movimientoService.buscarMovimientosEnCuenta(anyString(), anyString(), any(), any()))
                .thenReturn(ReporteResponseConstante.MOVIMIENTOS_POR_FECHA_POR_CUENTA);

        ResponseEntity<List<MovimientoPorFechaPorCuentaResponse>> response =
                reporteController.movimientosPorCuentaTipoCuentaYFechas(
                        ReporteRequestConstante.NUMERO_CUENTA,
                        ReporteRequestConstante.TIPO_CUENTA_DESCRIPCION,
                        ReporteRequestConstante.DESDE,
                        ReporteRequestConstante.HASTA
                );

        verify(movimientoService).buscarMovimientosEnCuenta(
                (eq(ReporteRequestConstante.NUMERO_CUENTA)),
                (eq(ReporteRequestConstante.TIPO_CUENTA_DESCRIPCION)),
                (eq(ReporteRequestConstante.DESDE)),
                (eq(ReporteRequestConstante.HASTA)))
        ;

        assertTrue(response.getStatusCode().value() == HttpStatus.OK.value());
        assertEquals(response.getBody().size(), 2);
        assertEquals(response.getBody().get(0).getSaldoInicial(), ReporteResponseConstante.SALDO_INICIAL);
    }
}