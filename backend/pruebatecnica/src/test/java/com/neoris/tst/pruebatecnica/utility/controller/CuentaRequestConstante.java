package com.neoris.tst.pruebatecnica.utility.controller;

import com.neoris.tst.pruebatecnica.request.ActivarCuentaRequest;
import com.neoris.tst.pruebatecnica.request.CrearCuentaUsuarioRequest;
import com.neoris.tst.pruebatecnica.request.EliminarCuentaRequest;
import com.neoris.tst.pruebatecnica.request.InactivarCuentaRequest;

import java.math.BigDecimal;

public class CuentaRequestConstante {

    public static String NUMERO_CUENTA = "478758";
    public static String TIPO_CUENTA_DESCRIPCION = "Ahorros";
    public static String NOMBRE_CLIENTE = "Jose Lema";
    public static BigDecimal SALDO = BigDecimal.valueOf(1000);
    public static String IDENTIFICACION_CLIENTE = "101234";
    public static CrearCuentaUsuarioRequest CREAR_CUENTA_USUARIO_REQUEST_OK = CrearCuentaUsuarioRequest.builder()
            .numeroCuenta("478758")
            .tipoCuentaDescripcion("Ahorros")
            .saldoInicial(BigDecimal.valueOf(2000))
            .nombreCliente("Jose Lema")
            .build();

    public static CrearCuentaUsuarioRequest CREAR_CUENTA_USUARIO_REQUEST_CUENTA_NULL = CrearCuentaUsuarioRequest.builder()
            .tipoCuentaDescripcion("Ahorros")
            .saldoInicial(BigDecimal.valueOf(2000))
            .nombreCliente("Jose Lema")
            .build();

    public static InactivarCuentaRequest INACTIVAR_CUENTA_REQUEST_OK = InactivarCuentaRequest.builder()
            .numeroCuenta(NUMERO_CUENTA).tipoCuentaDescripcion(TIPO_CUENTA_DESCRIPCION).identificacion(IDENTIFICACION_CLIENTE).build();

    public static InactivarCuentaRequest INACTIVAR_CUENTA_REQUEST_NUMERO_CUENTA_NULL = InactivarCuentaRequest.builder()
            .tipoCuentaDescripcion(TIPO_CUENTA_DESCRIPCION).identificacion(IDENTIFICACION_CLIENTE).build();

    public static ActivarCuentaRequest ACTIVAR_CUENTA_REQUEST_OK = ActivarCuentaRequest.builder()
            .numeroCuenta(NUMERO_CUENTA).tipoCuentaDescripcion(TIPO_CUENTA_DESCRIPCION).identificacion(IDENTIFICACION_CLIENTE).build();

    public static ActivarCuentaRequest ACTIVAR_CUENTA_REQUEST_NUMERO_CUENTA_NULL = ActivarCuentaRequest.builder()
            .tipoCuentaDescripcion(TIPO_CUENTA_DESCRIPCION).identificacion(IDENTIFICACION_CLIENTE).build();

    public static EliminarCuentaRequest ELIMINAR_CUENTA_REQUEST_OK = EliminarCuentaRequest.builder()
            .numeroCuenta(NUMERO_CUENTA).tipoCuentaDescripcion(TIPO_CUENTA_DESCRIPCION).force(true).build();

    public static EliminarCuentaRequest ELIMINAR_CUENTA_REQUEST_NUMERO_CUENTA_NULL = EliminarCuentaRequest.builder()
            .tipoCuentaDescripcion(TIPO_CUENTA_DESCRIPCION).force(true).build();
}
