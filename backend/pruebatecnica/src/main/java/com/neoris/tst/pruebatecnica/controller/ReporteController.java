package com.neoris.tst.pruebatecnica.controller;

import com.neoris.tst.pruebatecnica.exception.ClienteException;
import com.neoris.tst.pruebatecnica.exception.PersonaException;
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

    @GetMapping
    public ResponseEntity<List<MovimientoPorFechaPorUsuarioResponse>> movimientosPorClienteYFechas(
            @RequestParam @NotNull @NotEmpty String identificacion,
            @RequestParam @NotNull @NotEmpty LocalDate desde,
            @RequestParam @NotNull @NotEmpty LocalDate hasta
    )
            throws PersonaException, ClienteException {
        return ResponseEntity.ok(movimientoService.
                buscarMovimientosEnLasCuentasDeUnCliente(identificacion, desde, hasta));
    }

}
