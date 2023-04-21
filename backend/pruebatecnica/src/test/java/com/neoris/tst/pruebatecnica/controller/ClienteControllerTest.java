package com.neoris.tst.pruebatecnica.controller;

import com.neoris.tst.pruebatecnica.exception.ClienteException;
import com.neoris.tst.pruebatecnica.exception.GeneroException;
import com.neoris.tst.pruebatecnica.exception.PersonaException;
import com.neoris.tst.pruebatecnica.repository.ClienteRepository;
import com.neoris.tst.pruebatecnica.repository.PersonaRepository;
import com.neoris.tst.pruebatecnica.response.*;
import com.neoris.tst.pruebatecnica.service.ClienteService;
import com.neoris.tst.pruebatecnica.service.PersonaService;
import com.neoris.tst.pruebatecnica.utility.controller.ClienteRequestConstante;
import com.neoris.tst.pruebatecnica.utility.controller.ClienteResponseConstante;
import com.neoris.tst.pruebatecnica.utility.validations.ActivarInactivarUsuarioValidate;
import com.neoris.tst.pruebatecnica.utility.validations.CrearModificarUsuarioValidate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.neoris.tst.pruebatecnica.utility.MensajeExcepcionService.CLIENTE_ELIMINADO;
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

    @MockBean
    private ClienteRepository clienteRepository;

    @MockBean
    private PersonaRepository personaRepository;

    @Test
    void crearUsuario() throws GeneroException, PersonaException {
        when(clienteService.crearUsuario(any())).thenReturn(ClienteResponseConstante.CREAR_USUARIO_RESPONSE_OK);
        ResponseEntity<CrearUsuarioResponse> response = clienteController.crearUsuario(ClienteRequestConstante.CREAR_USUARIO_REQUEST_OK);
        verify(clienteService).crearUsuario(eq(ClienteRequestConstante.CREAR_USUARIO_REQUEST_OK));
        assertTrue(response.getStatusCode().value() == HttpStatus.CREATED.value());
    }

    @Test
    public void crearUsuario_datosInvalidos_excepcionEsperada() {
        try {
            clienteController.crearUsuario(ClienteRequestConstante.CREAR_USUARIO_NOMBRE_NULL);
        } catch (Exception e) {
            assertEquals(CrearModificarUsuarioValidate.NOMBRE_REQUIRED, e.getMessage());
        }
    }

    @Test
    void modificarUsuario() throws GeneroException, PersonaException, ClienteException {
        when(clienteService.modificarUsuario(any())).thenReturn(ClienteResponseConstante.MODIFICAR_USUARIO_RESPONSE_OK);

        ResponseEntity<ModificarUsuarioResponse> response =
                clienteController.modificarUsuario(ClienteRequestConstante.MODIFICAR_USUARIO_REQUEST_OK);

        verify(clienteService).modificarUsuario(eq(ClienteRequestConstante.MODIFICAR_USUARIO_REQUEST_OK));

        assertTrue(response.getStatusCode().value() == HttpStatus.OK.value());
        assertEquals(response.getBody().getNombres(), ClienteResponseConstante.MODIFICAR_USUARIO_RESPONSE_OK.getNombres());
    }

    @Test
    public void modificarUsuario_datosInvalidos_excepcionEsperada() {
        try {
            clienteController.modificarUsuario(ClienteRequestConstante.MODIFICAR_USUARIO_IDENTIFICACION_NULL);
        } catch (Exception e) {
            assertEquals(CrearModificarUsuarioValidate.IDENTIFICACION_NOT_NULL, e.getMessage());
        }
    }

    @Test
    void inactivarUsuario() throws PersonaException, ClienteException {
        when(clienteService.inactivarUsuario(any())).thenReturn(ClienteResponseConstante.INACTIVAR_USUARIO_RESPONSE_OK);

        ResponseEntity<InactivarUsuarioResponse> response =
                clienteController.inactivarUsuario(ClienteRequestConstante.INACTIVAR_USUARIO_REQUEST_OK);

        verify(clienteService).inactivarUsuario(eq(ClienteRequestConstante.INACTIVAR_USUARIO_REQUEST_OK));

        assertTrue(response.getStatusCode().value() == HttpStatus.OK.value());
        assertEquals(response.getBody().getNombre(), ClienteResponseConstante.INACTIVAR_USUARIO_RESPONSE_OK.getNombre());
        assertFalse(response.getBody().getEstado());
    }

    @Test
    public void inactivarUsuario_datosInvalidos_excepcionEsperada() {
        try {
            clienteController.inactivarUsuario(ClienteRequestConstante.INACTIVAR_USUARIO_REQUEST_IDENTIFICACION_NULL);
        } catch (Exception e) {
            assertEquals(ActivarInactivarUsuarioValidate.IDENTIFICACION_NOT_NULL, e.getMessage());
        }
    }

    @Test
    void activarUsuario() throws PersonaException, ClienteException {
        when(clienteService.activarUsuario(any())).thenReturn(ClienteResponseConstante.ACTIVAR_USUARIO_RESPONSE_OK);

        ResponseEntity<ActivarUsuarioResponse> response =
                clienteController.activarUsuario(ClienteRequestConstante.ACTIVAR_USUARIO_REQUEST_OK);

        verify(clienteService).activarUsuario(eq(ClienteRequestConstante.ACTIVAR_USUARIO_REQUEST_OK));

        assertTrue(response.getStatusCode().value() == HttpStatus.OK.value());
        assertEquals(response.getBody().getNombre(), ClienteResponseConstante.ACTIVAR_USUARIO_RESPONSE_OK.getNombre());
        assertTrue(response.getBody().getEstado());
    }

    @Test
    public void activarUsuario_datosInvalidos_excepcionEsperada() {
        try {
            clienteController.activarUsuario(ClienteRequestConstante.ACTIVAR_USUARIO_REQUEST_IDENTIFICACION_NULL);
        } catch (Exception e) {
            assertEquals(ActivarInactivarUsuarioValidate.IDENTIFICACION_NOT_NULL, e.getMessage());
        }
    }

    @Test
    void buscarUsuario() throws PersonaException, ClienteException {

        when(clienteService.buscarUsuarioPorIdentificacion(any())).thenReturn(ClienteResponseConstante.BUSCAR_USUARIO);

        ResponseEntity<BuscarUsuarioResponse> response =
                clienteController.buscarUsuario("1111");

        assertTrue(response.getStatusCode().value() == HttpStatus.OK.value());
        assertEquals(response.getBody().getNombres(), ClienteResponseConstante.PERSONA.getNombre());
        assertTrue(response.getBody().getEstado());
    }

    @Test
    void buscarUsuario_ExcepcionEsperada() {
        try {
            clienteController.buscarUsuario(null);
        } catch (Exception e) {
            assertEquals(ActivarInactivarUsuarioValidate.IDENTIFICACION_NOT_NULL, e.getMessage());
        }
    }

    @Test
    void buscarUsuarios() {

        when(clienteService.buscarTodosLosClientes()).thenReturn(ClienteResponseConstante.BUSCAR_USUARIOS);

        ResponseEntity<List<BuscarUsuarioResponse>> response =
                clienteController.buscarUsuarios();

        assertTrue(response.getStatusCode().value() == HttpStatus.OK.value());
        assertEquals(response.getBody().size(), 2);
        assertTrue(response.getBody().get(1).getEstado());
    }

    @Test
    void buscarUsuariosPorEstado() {

        when(clienteService.buscarTodosLosClientesPorEstado(Boolean.TRUE)).thenReturn(ClienteResponseConstante.BUSCAR_USUARIOS);

        ResponseEntity<List<BuscarUsuarioResponse>> response =
                clienteController.buscarTodosLosClientesPorEstado(Boolean.TRUE);

        assertTrue(response.getStatusCode().value() == HttpStatus.OK.value());
        assertEquals(response.getBody().size(), 2);
        assertTrue(response.getBody().get(1).getEstado());
    }

    @Test
    void eliminarUsuario() throws PersonaException, ClienteException {

        when(clienteService.eliminarUsuario(any())).thenReturn(String.format(CLIENTE_ELIMINADO, ClienteResponseConstante.PERSONA.getNombre()));

        ResponseEntity<String> response =
                clienteController.eliminarUsuario("11111");

        assertTrue(response.getStatusCode().value() == HttpStatus.OK.value());
        assertEquals(response.getBody(),
                String.format(CLIENTE_ELIMINADO, ClienteResponseConstante.PERSONA.getNombre()));
    }

    @Test
    void eliminarUsuario_ExcepcionEsperada() {
        try {
            clienteController.eliminarUsuario(null);
        } catch (Exception e) {
            assertEquals(ActivarInactivarUsuarioValidate.IDENTIFICACION_NOT_NULL, e.getMessage());
        }
    }
}