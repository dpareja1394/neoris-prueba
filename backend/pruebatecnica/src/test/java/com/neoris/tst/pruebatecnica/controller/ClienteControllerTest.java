package com.neoris.tst.pruebatecnica.controller;

import com.neoris.tst.pruebatecnica.exception.ClienteException;
import com.neoris.tst.pruebatecnica.exception.GeneroException;
import com.neoris.tst.pruebatecnica.exception.PersonaException;
import com.neoris.tst.pruebatecnica.response.ActivarUsuarioResponse;
import com.neoris.tst.pruebatecnica.response.CrearUsuarioResponse;
import com.neoris.tst.pruebatecnica.response.InactivarUsuarioResponse;
import com.neoris.tst.pruebatecnica.response.ModificarUsuarioResponse;
import com.neoris.tst.pruebatecnica.service.ClienteService;
import com.neoris.tst.pruebatecnica.service.PersonaService;
import com.neoris.tst.pruebatecnica.utility.RequestConstante;
import com.neoris.tst.pruebatecnica.utility.ResponseConstante;
import com.neoris.tst.pruebatecnica.utility.validations.ActivarInactivarUsuarioValidate;
import com.neoris.tst.pruebatecnica.utility.validations.CrearModificarUsuarioValidate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClienteControllerTest {

    @Autowired
    private ClienteController clienteController;

    @MockBean
    private ClienteService clienteService;

    @MockBean
    private PersonaService personaService;

    @Test
    void crearUsuario() throws GeneroException, PersonaException {
        when(clienteService.crearUsuario(any())).thenReturn(ResponseConstante.CREAR_USUARIO_RESPONSE_OK);
        ResponseEntity<CrearUsuarioResponse> response = clienteController.crearUsuario(RequestConstante.CREAR_USUARIO_REQUEST_OK);
        verify(clienteService).crearUsuario(eq(RequestConstante.CREAR_USUARIO_REQUEST_OK));
        assertTrue(response.getStatusCode().value() == HttpStatus.CREATED.value());
    }

    @Test
    public void crearUsuario_datosInvalidos_excepcionEsperada() {
        try {
            clienteController.crearUsuario(RequestConstante.CREAR_USUARIO_NOMBRE_NULL);
        } catch (Exception e) {
            assertEquals(CrearModificarUsuarioValidate.NOMBRE_REQUIRED, e.getMessage());
        }
    }

    @Test
    void modificarUsuario() throws GeneroException, PersonaException, ClienteException {
        when(clienteService.modificarUsuario(any())).thenReturn(ResponseConstante.MODIFICAR_USUARIO_RESPONSE_OK);

        ResponseEntity<ModificarUsuarioResponse> response =
                clienteController.modificarUsuario(RequestConstante.MODIFICAR_USUARIO_REQUEST_OK);

        verify(clienteService).modificarUsuario(eq(RequestConstante.MODIFICAR_USUARIO_REQUEST_OK));

        assertTrue(response.getStatusCode().value() == HttpStatus.OK.value());
        assertEquals(response.getBody().getNombres(), ResponseConstante.MODIFICAR_USUARIO_RESPONSE_OK.getNombres());
    }

    @Test
    public void modificarUsuario_datosInvalidos_excepcionEsperada() {
        try {
            clienteController.modificarUsuario(RequestConstante.MODIFICAR_USUARIO_IDENTIFICACION_NULL);
        } catch (Exception e) {
            assertEquals(CrearModificarUsuarioValidate.IDENTIFICACION_NOT_NULL, e.getMessage());
        }
    }

    @Test
    void inactivarUsuario() throws PersonaException, ClienteException {
        when(clienteService.inactivarUsuario(any())).thenReturn(ResponseConstante.INACTIVAR_USUARIO_RESPONSE_OK);

        ResponseEntity<InactivarUsuarioResponse> response =
                clienteController.inactivarUsuario(RequestConstante.INACTIVAR_USUARIO_REQUEST_OK);

        verify(clienteService).inactivarUsuario(eq(RequestConstante.INACTIVAR_USUARIO_REQUEST_OK));

        assertTrue(response.getStatusCode().value() == HttpStatus.OK.value());
        assertEquals(response.getBody().getNombre(), ResponseConstante.INACTIVAR_USUARIO_RESPONSE_OK.getNombre());
        assertFalse(response.getBody().getEstado());
    }

    @Test
    public void inactivarUsuario_datosInvalidos_excepcionEsperada() {
        try {
            clienteController.inactivarUsuario(RequestConstante.INACTIVAR_USUARIO_REQUEST_IDENTIFICACION_NULL);
        } catch (Exception e) {
            assertEquals(ActivarInactivarUsuarioValidate.IDENTIFICACION_NOT_NULL, e.getMessage());
        }
    }

    @Test
    void activarUsuario() throws PersonaException, ClienteException {
        when(clienteService.activarUsuario(any())).thenReturn(ResponseConstante.ACTIVAR_USUARIO_RESPONSE_OK);

        ResponseEntity<ActivarUsuarioResponse> response =
                clienteController.activarUsuario(RequestConstante.ACTIVAR_USUARIO_REQUEST_OK);

        verify(clienteService).activarUsuario(eq(RequestConstante.ACTIVAR_USUARIO_REQUEST_OK));

        assertTrue(response.getStatusCode().value() == HttpStatus.OK.value());
        assertEquals(response.getBody().getNombre(), ResponseConstante.ACTIVAR_USUARIO_RESPONSE_OK.getNombre());
        assertTrue(response.getBody().getEstado());
    }

    @Test
    public void activarUsuario_datosInvalidos_excepcionEsperada() {
        try {
            clienteController.activarUsuario(RequestConstante.ACTIVAR_USUARIO_REQUEST_IDENTIFICACION_NULL);
        } catch (Exception e) {
            assertEquals(ActivarInactivarUsuarioValidate.IDENTIFICACION_NOT_NULL, e.getMessage());
        }
    }
}