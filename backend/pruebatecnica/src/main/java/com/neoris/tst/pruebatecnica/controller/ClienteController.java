package com.neoris.tst.pruebatecnica.controller;

import com.neoris.tst.pruebatecnica.exception.ClienteException;
import com.neoris.tst.pruebatecnica.exception.GeneroException;
import com.neoris.tst.pruebatecnica.exception.PersonaException;
import com.neoris.tst.pruebatecnica.request.CrearUsuarioRequest;
import com.neoris.tst.pruebatecnica.request.InactivarUsuarioRequest;
import com.neoris.tst.pruebatecnica.response.CrearUsuarioResponse;
import com.neoris.tst.pruebatecnica.response.InactivarUsuarioResponse;
import com.neoris.tst.pruebatecnica.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/neoristst/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;

    }

    @PostMapping
    public ResponseEntity<CrearUsuarioResponse> crearUsuario(@RequestBody @Valid CrearUsuarioRequest crearUsuarioRequest)
            throws GeneroException, PersonaException {
        return new ResponseEntity(clienteService.crearUsuario(crearUsuarioRequest), HttpStatus.CREATED);
    }

    @PostMapping("/inactivar")
    public ResponseEntity<InactivarUsuarioResponse> inactivarUsuario
            (@RequestBody @Valid InactivarUsuarioRequest inactivarUsuarioRequest)
            throws PersonaException, ClienteException {
        return ResponseEntity.ok(clienteService.inactivarUsuario(inactivarUsuarioRequest));
    }

}
