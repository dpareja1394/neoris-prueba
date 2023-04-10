package com.neoris.tst.pruebatecnica.controller;

import com.neoris.tst.pruebatecnica.exception.ClienteException;
import com.neoris.tst.pruebatecnica.exception.CuentaException;
import com.neoris.tst.pruebatecnica.exception.PersonaException;
import com.neoris.tst.pruebatecnica.exception.TipoCuentaException;
import com.neoris.tst.pruebatecnica.request.ActivarCuentaRequest;
import com.neoris.tst.pruebatecnica.request.CrearCuentaUsuarioRequest;
import com.neoris.tst.pruebatecnica.request.EliminarCuentaRequest;
import com.neoris.tst.pruebatecnica.request.InactivarCuentaRequest;
import com.neoris.tst.pruebatecnica.response.ActivarCuentaResponse;
import com.neoris.tst.pruebatecnica.response.BuscarCuentaResponse;
import com.neoris.tst.pruebatecnica.response.CrearCuentaUsuarioResponse;
import com.neoris.tst.pruebatecnica.response.InactivarCuentaResponse;
import com.neoris.tst.pruebatecnica.service.CuentaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
            throws CuentaException, PersonaException, TipoCuentaException, ClienteException {
        return new ResponseEntity(cuentaService.crearCuentaUsuario(crearCuentaUsuarioRequest), HttpStatus.CREATED);
    }

    @GetMapping("/consultar/{numeroCuenta}/{tipoCuenta}")
    public ResponseEntity<BuscarCuentaResponse> consultarCuenta(
            @PathVariable @NotNull @NotEmpty String numeroCuenta,
            @PathVariable @NotNull @NotEmpty String tipoCuenta)
            throws CuentaException, TipoCuentaException {
        return ResponseEntity.ok(cuentaService.consultarCuentaPorNumeroYTipoCuenta(numeroCuenta, tipoCuenta));
    }

    @GetMapping("/consultarPorUsuario/{identificacion}")
    public ResponseEntity<List<BuscarCuentaResponse>> consultarCuentasPorUsuario(
            @PathVariable @NotNull @NotEmpty String identificacion)
            throws CuentaException, TipoCuentaException, PersonaException, ClienteException {
        return ResponseEntity.ok(cuentaService.consultarCuentasPorUsuario(identificacion));
    }

    @PutMapping("/inactivar")
    public ResponseEntity<InactivarCuentaResponse> inactivarCuenta(
            @RequestBody @Valid InactivarCuentaRequest inactivarCuentaRequest)
            throws CuentaException, PersonaException, TipoCuentaException, ClienteException {
        return ResponseEntity.ok(cuentaService.inactivarCuenta(inactivarCuentaRequest));
    }

    @PutMapping("/activar")
    public ResponseEntity<ActivarCuentaResponse> activarCuenta(
            @RequestBody @Valid ActivarCuentaRequest activarCuentaRequest)
            throws PersonaException, ClienteException, CuentaException, TipoCuentaException {
        return ResponseEntity.ok(cuentaService.activarCuenta(activarCuentaRequest));
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminarCuenta(
            @RequestBody @Valid EliminarCuentaRequest eliminarCuentaRequest)
            throws CuentaException, TipoCuentaException {
        return ResponseEntity.ok(cuentaService.eliminarCuenta(eliminarCuentaRequest.getNumeroCuenta(),
                eliminarCuentaRequest.getTipoCuentaDescripcion(), eliminarCuentaRequest.isForce()));
    }

}
