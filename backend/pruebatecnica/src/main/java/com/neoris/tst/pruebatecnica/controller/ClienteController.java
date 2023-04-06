package com.neoris.tst.pruebatecnica.controller;

import com.neoris.tst.pruebatecnica.request.CrearUsuarioRequest;
import com.neoris.tst.pruebatecnica.response.CrearUsuarioResponse;
import com.neoris.tst.pruebatecnica.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/neoristst/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;

    }

    @PostMapping
    public ResponseEntity<CrearUsuarioResponse> crearUsuario(@RequestBody @Valid CrearUsuarioRequest crearUsuarioRequest) {
        return new ResponseEntity(clienteService.crearUsuario(crearUsuarioRequest), HttpStatus.CREATED);
    }

}
