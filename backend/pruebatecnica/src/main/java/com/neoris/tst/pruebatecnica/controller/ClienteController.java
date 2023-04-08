package com.neoris.tst.pruebatecnica.controller;

import com.neoris.tst.pruebatecnica.exception.ClienteException;
import com.neoris.tst.pruebatecnica.exception.GeneroException;
import com.neoris.tst.pruebatecnica.exception.PersonaException;
import com.neoris.tst.pruebatecnica.request.ActivarUsuarioRequest;
import com.neoris.tst.pruebatecnica.request.CrearUsuarioRequest;
import com.neoris.tst.pruebatecnica.request.InactivarUsuarioRequest;
import com.neoris.tst.pruebatecnica.request.ModificarUsuarioRequest;
import com.neoris.tst.pruebatecnica.response.*;
import com.neoris.tst.pruebatecnica.service.ClienteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/neoristst/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;

    }

    @PostMapping
    public ResponseEntity<CrearUsuarioResponse> crearUsuario(
            @RequestBody @Valid CrearUsuarioRequest crearUsuarioRequest)
            throws GeneroException, PersonaException {
        return new ResponseEntity(clienteService.crearUsuario(crearUsuarioRequest), HttpStatus.CREATED);
    }

    @PutMapping("/inactivar")
    public ResponseEntity<InactivarUsuarioResponse> inactivarUsuario(
            @RequestBody @Valid InactivarUsuarioRequest inactivarUsuarioRequest)
            throws PersonaException, ClienteException {
        return ResponseEntity.ok(clienteService.inactivarUsuario(inactivarUsuarioRequest));
    }

    @PutMapping("/activar")
    public ResponseEntity<ActivarUsuarioResponse> activarUsuario(
            @RequestBody @Valid ActivarUsuarioRequest activarUsuarioRequest)
            throws PersonaException, ClienteException {
        return ResponseEntity.ok(clienteService.activarUsuario(activarUsuarioRequest));
    }

    @PutMapping("/modificar")
    public ResponseEntity<ModificarUsuarioResponse> modificarUsuario(
            @RequestBody @Valid ModificarUsuarioRequest modificarUsuarioRequest)
            throws PersonaException, ClienteException, GeneroException {
        return ResponseEntity.ok(clienteService.modificarUsuario(modificarUsuarioRequest));
    }

    @GetMapping("/buscar/{identificacion}")
    public ResponseEntity<BuscarUsuarioResponse> buscarUsuario(
            @PathVariable @NotNull @NotEmpty String identificacion) throws PersonaException, ClienteException {
        return ResponseEntity.ok(clienteService.buscarUsuarioPorIdentificacion(identificacion));
    }

    @GetMapping("/buscarTodos")
    public ResponseEntity<List<BuscarUsuarioResponse>> buscarUsuarios() {
        return ResponseEntity.ok(clienteService.buscarTodosLosClientes());
    }

    @GetMapping("/buscarTodosPorEstado/{estado}")
    public ResponseEntity<List<BuscarUsuarioResponse>> buscarTodosLosClientesPorEstado(
            @PathVariable @NotNull Boolean estado) {
        return ResponseEntity.ok(clienteService.buscarTodosLosClientesPorEstado(estado));
    }

    @DeleteMapping("/eliminar/{identificacion}")
    public ResponseEntity<String> eliminarUsuario(
            @PathVariable @NotNull @NotEmpty String identificacion) throws PersonaException, ClienteException {
        return ResponseEntity.ok(clienteService.eliminarUsuario(identificacion));
    }

}
