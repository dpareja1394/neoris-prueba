package com.neoris.tst.pruebatecnica.controller;

import com.neoris.tst.pruebatecnica.exception.GeneroException;
import com.neoris.tst.pruebatecnica.exception.PersonaException;
import com.neoris.tst.pruebatecnica.response.CrearUsuarioResponse;
import com.neoris.tst.pruebatecnica.service.ClienteService;
import com.neoris.tst.pruebatecnica.service.PersonaService;
import com.neoris.tst.pruebatecnica.utility.RequestConstante;
import com.neoris.tst.pruebatecnica.utility.ResponseConstante;
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
class ClienteControllerTest {

    @Autowired
    private ClienteController clienteController;

    @MockBean
    private ClienteService clienteService;

    @MockBean
    private PersonaService personaService;

    @Test
    void crearUsuario() throws GeneroException, PersonaException {
        // Definir el comportamiento deseado del servicio
        when(clienteService.crearUsuario(any())).thenReturn(ResponseConstante.CREAR_USUARIO_RESPONSE_OK);

        // Invocar el controlador para concretar la solicitud
        ResponseEntity<CrearUsuarioResponse> response = clienteController.crearUsuario(RequestConstante.CREAR_USUARIO_REQUEST_OK);

        // Verificar que el servicio fue llamado correctamente
        verify(clienteService).crearUsuario(eq(RequestConstante.CREAR_USUARIO_REQUEST_OK));

        // Verificar que el resultado sea exitoso
        assertTrue(response.getStatusCode().value() == HttpStatus.CREATED.value());
    }

    @Test
    public void crearUsuario_datosInvalidos_excepcionEsperada() {
        // Invocar el controlador para concretar la solicitud
        ResponseEntity<CrearUsuarioResponse> response = null;
        try {
            response = clienteController.crearUsuario(RequestConstante.CREAR_USUARIO_NOMBRE_NULL);
        } catch (Exception e) {
            assertEquals("El campo nombres es obligatorio", e.getMessage());
        }

    }
}