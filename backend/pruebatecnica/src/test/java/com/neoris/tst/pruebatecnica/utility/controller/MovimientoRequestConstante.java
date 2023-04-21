package com.neoris.tst.pruebatecnica.utility.controller;

import com.neoris.tst.pruebatecnica.request.RealizarMovimientoRequest;

import java.math.BigDecimal;

public class MovimientoRequestConstante {

    public static String NUMERO_CUENTA = "478758";
    public static String TIPO_CUENTA_DESCRIPCION = "Ahorros";
    public static String TIPO_MOVIMIENTO_DESCRIPCION = "Deposito";
    public static BigDecimal VALOR = BigDecimal.valueOf(1000);

    public static RealizarMovimientoRequest REALIZAR_MOVIMIENTO_REQUEST_OK =
            RealizarMovimientoRequest.builder()
                    .numeroCuenta(NUMERO_CUENTA)
                    .tipoCuentaDescripcion(TIPO_CUENTA_DESCRIPCION)
                    .tipoMovimientoDescripcion(TIPO_MOVIMIENTO_DESCRIPCION)
                    .valor(VALOR).build();

    public static RealizarMovimientoRequest REALIZAR_MOVIMIENTO_REQUEST_NUMERO_CUENTA_NULL =
            RealizarMovimientoRequest.builder()
                    .tipoCuentaDescripcion(TIPO_CUENTA_DESCRIPCION)
                    .tipoMovimientoDescripcion(TIPO_MOVIMIENTO_DESCRIPCION)
                    .valor(VALOR).build();

}
