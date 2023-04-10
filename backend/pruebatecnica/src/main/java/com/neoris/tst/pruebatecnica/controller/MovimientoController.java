package com.neoris.tst.pruebatecnica.controller;

import com.neoris.tst.pruebatecnica.exception.CuentaException;
import com.neoris.tst.pruebatecnica.exception.MovimientoException;
import com.neoris.tst.pruebatecnica.exception.TipoCuentaException;
import com.neoris.tst.pruebatecnica.exception.TipoMovimientoException;
import com.neoris.tst.pruebatecnica.request.RealizarMovimientoRequest;
import com.neoris.tst.pruebatecnica.response.RealizarMovimientoResponse;
import com.neoris.tst.pruebatecnica.service.MovimientoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/neoristst/movimientos")
public class MovimientoController {

    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping
    public ResponseEntity<RealizarMovimientoResponse> realizarMovimiento
            (@RequestBody @Valid RealizarMovimientoRequest realizarMovimientoRequest) throws CuentaException, TipoCuentaException, TipoMovimientoException, MovimientoException {
        return new ResponseEntity(movimientoService.realizarMovimiento(realizarMovimientoRequest), HttpStatus.CREATED);
    }

}
