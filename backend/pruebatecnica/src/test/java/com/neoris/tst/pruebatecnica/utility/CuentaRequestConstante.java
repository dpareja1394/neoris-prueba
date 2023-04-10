package com.neoris.tst.pruebatecnica.utility;

import com.neoris.tst.pruebatecnica.request.CrearCuentaUsuarioRequest;

import java.math.BigDecimal;

public class CuentaRequestConstante {

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

}
