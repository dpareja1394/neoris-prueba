package com.neoris.tst.pruebatecnica.utility;

import com.neoris.tst.pruebatecnica.response.CrearCuentaUsuarioResponse;

public class CuentaResponseConstante {

    public static CrearCuentaUsuarioResponse CREAR_CUENTA_USUARIO_RESPONSE = CrearCuentaUsuarioResponse.builder()
            .numeroCuenta("478758")
            .tipoCuentaDescripcion("Ahorros")
            .nombreCliente("Jose Lema")
            .estado(true)
            .build();

}
