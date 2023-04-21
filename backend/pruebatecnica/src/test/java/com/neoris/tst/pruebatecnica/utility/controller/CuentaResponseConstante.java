package com.neoris.tst.pruebatecnica.utility.controller;

import com.neoris.tst.pruebatecnica.response.ActivarCuentaResponse;
import com.neoris.tst.pruebatecnica.response.BuscarCuentaResponse;
import com.neoris.tst.pruebatecnica.response.CrearCuentaUsuarioResponse;
import com.neoris.tst.pruebatecnica.response.InactivarCuentaResponse;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class CuentaResponseConstante {

    public static String NUMERO_CUENTA = "478758";
    public static String TIPO_CUENTA_DESCRIPCION = "Ahorros";
    public static String NOMBRE_CLIENTE = "Jose Lema";
    public static BigDecimal SALDO = BigDecimal.valueOf(1000);
    public static String IDENTIFICACION_CLIENTE = "101234";

    public static CrearCuentaUsuarioResponse CREAR_CUENTA_USUARIO_RESPONSE = CrearCuentaUsuarioResponse.builder()
            .numeroCuenta(NUMERO_CUENTA)
            .tipoCuentaDescripcion(TIPO_CUENTA_DESCRIPCION)
            .nombreCliente(NOMBRE_CLIENTE)
            .estado(true)
            .build();

    public static BuscarCuentaResponse BUSCAR_CUENTA_RESPONSE = BuscarCuentaResponse.builder()
            .numeroCuenta(NUMERO_CUENTA)
            .cliente(NOMBRE_CLIENTE)
            .tipoCuenta(TIPO_CUENTA_DESCRIPCION)
            .saldoActual(SALDO)
            .estado(Boolean.TRUE)
            .build();

    public static List<BuscarCuentaResponse> BUSCAR_CUENTAS =
            Arrays.asList(
                    BuscarCuentaResponse.builder().numeroCuenta("478758").cliente("Jose Lema").tipoCuenta("Ahorros")
                            .saldoActual(BigDecimal.valueOf(2000)).estado(Boolean.TRUE).build(),
                    BuscarCuentaResponse.builder().numeroCuenta("585545").cliente("Jose Lema").tipoCuenta("Corriente")
                            .saldoActual(BigDecimal.valueOf(1000)).estado(Boolean.FALSE).build()
            );

    public static InactivarCuentaResponse INACTIVAR_CUENTA_RESPONSE = InactivarCuentaResponse.builder()
            .numeroCuenta(NUMERO_CUENTA).tipoCuentaDescripcion(TIPO_CUENTA_DESCRIPCION)
            .estado(Boolean.FALSE).identificacion(IDENTIFICACION_CLIENTE).build();

    public static ActivarCuentaResponse ACTIVAR_CUENTA_RESPONSE = ActivarCuentaResponse.builder()
            .numeroCuenta(NUMERO_CUENTA).tipoCuentaDescripcion(TIPO_CUENTA_DESCRIPCION)
            .estado(Boolean.TRUE).identificacion(IDENTIFICACION_CLIENTE).build();

}
