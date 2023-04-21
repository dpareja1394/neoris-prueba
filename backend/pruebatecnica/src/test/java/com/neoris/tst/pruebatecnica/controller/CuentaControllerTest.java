package com.neoris.tst.pruebatecnica.controller;

import com.neoris.tst.pruebatecnica.exception.ClienteException;
import com.neoris.tst.pruebatecnica.exception.CuentaException;
import com.neoris.tst.pruebatecnica.exception.PersonaException;
import com.neoris.tst.pruebatecnica.exception.TipoCuentaException;
import com.neoris.tst.pruebatecnica.response.ActivarCuentaResponse;
import com.neoris.tst.pruebatecnica.response.BuscarCuentaResponse;
import com.neoris.tst.pruebatecnica.response.CrearCuentaUsuarioResponse;
import com.neoris.tst.pruebatecnica.response.InactivarCuentaResponse;
import com.neoris.tst.pruebatecnica.service.CuentaService;
import com.neoris.tst.pruebatecnica.utility.controller.CuentaRequestConstante;
import com.neoris.tst.pruebatecnica.utility.controller.CuentaResponseConstante;
import com.neoris.tst.pruebatecnica.utility.validations.ActivarInactivarCuentaValidate;
import com.neoris.tst.pruebatecnica.utility.validations.CrearCuentaUsuarioValidate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.neoris.tst.pruebatecnica.utility.MensajeExcepcionService.CUENTA_ELIMINADA;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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

    @Test
    void consultarCuenta() throws CuentaException, TipoCuentaException {
        when(cuentaService.consultarCuentaPorNumeroYTipoCuenta(
                any(), any()
        ))
                .thenReturn(CuentaResponseConstante.BUSCAR_CUENTA_RESPONSE);

        ResponseEntity<BuscarCuentaResponse> response =
                cuentaController.consultarCuenta(CuentaRequestConstante.NUMERO_CUENTA, CuentaRequestConstante.TIPO_CUENTA_DESCRIPCION);

        verify(cuentaService).consultarCuentaPorNumeroYTipoCuenta
                (eq(CuentaRequestConstante.NUMERO_CUENTA), eq(CuentaRequestConstante.TIPO_CUENTA_DESCRIPCION));

        assertTrue(response.getStatusCode().value() == HttpStatus.OK.value());
        assertEquals(response.getBody().getNumeroCuenta(), CuentaResponseConstante.CREAR_CUENTA_USUARIO_RESPONSE.getNumeroCuenta());
        assertTrue(response.getBody().getEstado());
    }

    @Test
    void consultarCuenta_ExcepcionEsperada() {
        try {
            cuentaController.consultarCuenta(null, CuentaRequestConstante.TIPO_CUENTA_DESCRIPCION);
        } catch (Exception e) {
            assertEquals(CrearCuentaUsuarioValidate.NUMERO_CUENTA_REQUIRED, e.getMessage());
        }
    }

    @Test
    void consultarCuentasPorUsuario() throws CuentaException, PersonaException, TipoCuentaException, ClienteException {
        when(cuentaService.consultarCuentasPorUsuario(any()))
                .thenReturn(CuentaResponseConstante.BUSCAR_CUENTAS);

        ResponseEntity<List<BuscarCuentaResponse>> response =
                cuentaController.consultarCuentasPorUsuario("101234");

        verify(cuentaService).consultarCuentasPorUsuario
                (eq("101234"));

        assertTrue(response.getStatusCode().value() == HttpStatus.OK.value());
        assertEquals(response.getBody().size(), 2);
        assertFalse(response.getBody().get(1).getEstado());
    }

    @Test
    void inactivarCuenta() throws CuentaException, PersonaException, TipoCuentaException, ClienteException {
        when(cuentaService.inactivarCuenta(any())).thenReturn(CuentaResponseConstante.INACTIVAR_CUENTA_RESPONSE);

        ResponseEntity<InactivarCuentaResponse> response =
                cuentaController.inactivarCuenta(CuentaRequestConstante.INACTIVAR_CUENTA_REQUEST_OK);

        verify(cuentaService).inactivarCuenta(eq(CuentaRequestConstante.INACTIVAR_CUENTA_REQUEST_OK));

        assertTrue(response.getStatusCode().value() == HttpStatus.OK.value());
        assertEquals(response.getBody().getNumeroCuenta(), CuentaResponseConstante.NUMERO_CUENTA);
        assertFalse(response.getBody().getEstado());
    }

    @Test
    public void inactivarCuenta_datosInvalidos_excepcionEsperada() {
        try {
            cuentaController.inactivarCuenta(CuentaRequestConstante.INACTIVAR_CUENTA_REQUEST_NUMERO_CUENTA_NULL);
        } catch (Exception e) {
            assertEquals(ActivarInactivarCuentaValidate.NUMERO_CUENTA_REQUIRED, e.getMessage());
        }
    }

    @Test
    void activarCuenta() throws CuentaException, PersonaException, TipoCuentaException, ClienteException {
        when(cuentaService.activarCuenta(any())).thenReturn(CuentaResponseConstante.ACTIVAR_CUENTA_RESPONSE);

        ResponseEntity<ActivarCuentaResponse> response =
                cuentaController.activarCuenta(CuentaRequestConstante.ACTIVAR_CUENTA_REQUEST_OK);

        verify(cuentaService).activarCuenta(eq(CuentaRequestConstante.ACTIVAR_CUENTA_REQUEST_OK));

        assertTrue(response.getStatusCode().value() == HttpStatus.OK.value());
        assertEquals(response.getBody().getNumeroCuenta(), CuentaResponseConstante.NUMERO_CUENTA);
        assertTrue(response.getBody().getEstado());
    }

    @Test
    public void activarCuenta_datosInvalidos_excepcionEsperada() {
        try {
            cuentaController.activarCuenta(CuentaRequestConstante.ACTIVAR_CUENTA_REQUEST_NUMERO_CUENTA_NULL);
        } catch (Exception e) {
            assertEquals(ActivarInactivarCuentaValidate.NUMERO_CUENTA_REQUIRED, e.getMessage());
        }
    }

    @Test
    void eliminarCuenta() throws CuentaException, TipoCuentaException {
        String mensajeExito = String.format(CUENTA_ELIMINADA, CuentaResponseConstante.TIPO_CUENTA_DESCRIPCION,
                CuentaResponseConstante.NUMERO_CUENTA);
        when(cuentaService.eliminarCuenta(anyString(), anyString(), anyBoolean())).thenReturn(mensajeExito);

        ResponseEntity<String> response =
                cuentaController.eliminarCuenta(CuentaRequestConstante.ELIMINAR_CUENTA_REQUEST_OK);

        verify(cuentaService).eliminarCuenta(eq(CuentaRequestConstante.NUMERO_CUENTA),
                eq(CuentaRequestConstante.TIPO_CUENTA_DESCRIPCION), eq(true));

        assertTrue(response.getStatusCode().value() == HttpStatus.OK.value());
        assertEquals(response.getBody(),
                mensajeExito);
    }

    @Test
    void eliminarCuenta_ExcepcionEsperada() {
        try {
            cuentaController.eliminarCuenta(CuentaRequestConstante.ELIMINAR_CUENTA_REQUEST_NUMERO_CUENTA_NULL);
        } catch (Exception e) {
            assertEquals(ActivarInactivarCuentaValidate.NUMERO_CUENTA_REQUIRED, e.getMessage());
        }
    }
}