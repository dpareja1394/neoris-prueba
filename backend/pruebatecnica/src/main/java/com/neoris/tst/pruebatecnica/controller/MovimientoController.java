package com.neoris.tst.pruebatecnica.controller;

import com.neoris.tst.pruebatecnica.exception.ClienteNoExistePorIdentificacion;
import com.neoris.tst.pruebatecnica.exception.CuentaExistePorClienteTipoCuentaEstado;
import com.neoris.tst.pruebatecnica.exception.PersonaNoExistePorNombre;
import com.neoris.tst.pruebatecnica.exception.TipoCuentaNoExistePorDescripcion;
import com.neoris.tst.pruebatecnica.request.CrearCuentaUsuarioRequest;
import com.neoris.tst.pruebatecnica.request.RealizarMovimientoRequest;
import com.neoris.tst.pruebatecnica.response.CrearCuentaUsuarioResponse;
import com.neoris.tst.pruebatecnica.response.RealizarMovimientoResponse;
import com.neoris.tst.pruebatecnica.service.CuentaService;
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

    @PostMapping
    public ResponseEntity<RealizarMovimientoResponse> realizarMovimiento
            (@RequestBody @Valid RealizarMovimientoRequest realizarMovimientoRequest) {
        return new ResponseEntity(RealizarMovimientoResponse.builder().build(), HttpStatus.CREATED);
    }

}
