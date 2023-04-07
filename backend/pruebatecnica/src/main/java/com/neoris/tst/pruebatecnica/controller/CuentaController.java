package com.neoris.tst.pruebatecnica.controller;

import com.neoris.tst.pruebatecnica.exception.*;
import com.neoris.tst.pruebatecnica.request.CrearCuentaUsuarioRequest;
import com.neoris.tst.pruebatecnica.response.CrearCuentaUsuarioResponse;
import com.neoris.tst.pruebatecnica.service.CuentaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/neoristst/cuentas")
public class CuentaController {
    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }


    @PostMapping
    public ResponseEntity<CrearCuentaUsuarioResponse> crearCuentaUsuario
            (@RequestBody @Valid CrearCuentaUsuarioRequest crearCuentaUsuarioRequest)
            throws TipoCuentaNoExistePorDescripcion, CuentaExistePorClienteTipoCuentaEstado,
            PersonaNoExistePorNombre, ClienteNoExistePorIdentificacion, ClienteNoExistePorNombreYEstado {
        return new ResponseEntity(cuentaService.crearCuentaUsuario(crearCuentaUsuarioRequest), HttpStatus.CREATED);
    }

}
