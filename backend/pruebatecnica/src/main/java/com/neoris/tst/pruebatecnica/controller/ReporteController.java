package com.neoris.tst.pruebatecnica.controller;

import com.neoris.tst.pruebatecnica.exception.ClienteException;
import com.neoris.tst.pruebatecnica.exception.CuentaException;
import com.neoris.tst.pruebatecnica.exception.PersonaException;
import com.neoris.tst.pruebatecnica.exception.TipoCuentaException;
import com.neoris.tst.pruebatecnica.response.MovimientoPorFechaPorCuentaResponse;
import com.neoris.tst.pruebatecnica.response.MovimientoPorFechaPorUsuarioResponse;
import com.neoris.tst.pruebatecnica.service.MovimientoService;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/neoristst/reportes")
public class ReporteController {

    private final MovimientoService movimientoService;

    public ReporteController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @GetMapping(path = "/clienteFechas")
    public ResponseEntity<List<MovimientoPorFechaPorUsuarioResponse>> movimientosPorClienteYFechas(
            @RequestParam @NotNull @NotEmpty String identificacion,
            @RequestParam @NotNull @NotEmpty LocalDate desde,
            @RequestParam @NotNull @NotEmpty LocalDate hasta
    )
            throws PersonaException, ClienteException {
        return ResponseEntity.ok(movimientoService.
                buscarMovimientosEnLasCuentasDeUnCliente(identificacion, desde, hasta));
    }

    @GetMapping(path = "/cuentaTipoCuentaFechas")
    public ResponseEntity<List<MovimientoPorFechaPorCuentaResponse>> movimientosPorCuentaTipoCuentaYFechas(
            @RequestParam @NotNull @NotEmpty String numeroCuenta,
            @RequestParam @NotNull @NotEmpty String tipoCuenta,
            @RequestParam @NotNull @NotEmpty LocalDate desde,
            @RequestParam @NotNull @NotEmpty LocalDate hasta
    )
            throws CuentaException, TipoCuentaException {
        return ResponseEntity.ok(movimientoService.
                buscarMovimientosEnCuenta(numeroCuenta, tipoCuenta, desde, hasta));
    }

}
