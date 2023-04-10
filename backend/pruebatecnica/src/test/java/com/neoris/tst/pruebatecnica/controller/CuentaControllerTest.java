package com.neoris.tst.pruebatecnica.controller;

import com.neoris.tst.pruebatecnica.exception.ClienteException;
import com.neoris.tst.pruebatecnica.exception.CuentaException;
import com.neoris.tst.pruebatecnica.exception.PersonaException;
import com.neoris.tst.pruebatecnica.exception.TipoCuentaException;
import com.neoris.tst.pruebatecnica.response.CrearCuentaUsuarioResponse;
import com.neoris.tst.pruebatecnica.service.CuentaService;
import com.neoris.tst.pruebatecnica.utility.CuentaRequestConstante;
import com.neoris.tst.pruebatecnica.utility.CuentaResponseConstante;
import com.neoris.tst.pruebatecnica.utility.validations.CrearCuentaUsuarioValidate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CuentaControllerTest {

    @Autowired
    private CuentaController cuentaController;

    @MockBean
    private CuentaService cuentaService;

    @Test
    void crearCuentaUsuario() throws CuentaException, PersonaException, TipoCuentaException, ClienteException {
        when(cuentaService.crearCuentaUsuario(any())).thenReturn(CuentaResponseConstante.CREAR_CUENTA_USUARIO_RESPONSE);
        ResponseEntity<CrearCuentaUsuarioResponse> response =
                cuentaController.crearCuentaUsuario(CuentaRequestConstante.CREAR_CUENTA_USUARIO_REQUEST_OK);
        verify(cuentaService).crearCuentaUsuario(eq(CuentaRequestConstante.CREAR_CUENTA_USUARIO_REQUEST_OK));
        assertTrue(response.getStatusCode().value() == HttpStatus.CREATED.value());
    }

    @Test
    void crearCuentaUsuario_datosInvalidos_excepcionEsperada() {
        try {
            cuentaController.crearCuentaUsuario(CuentaRequestConstante.CREAR_CUENTA_USUARIO_REQUEST_CUENTA_NULL);
        } catch (Exception e) {
            assertEquals(CrearCuentaUsuarioValidate.NUMERO_CUENTA_REQUIRED, e.getMessage());
        }
    }
}