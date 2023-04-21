package com.neoris.tst.pruebatecnica.utility.controller;

import com.neoris.tst.pruebatecnica.response.RealizarMovimientoResponse;

import java.math.BigDecimal;

public class MovimientoResponseConstante {

    public static String NUMERO_CUENTA = "478758";
    public static String TIPO_CUENTA_DESCRIPCION = "Ahorros";
    public static BigDecimal SALDO_INICIAL = BigDecimal.ZERO;
    public static String TIPO_MOVIMIENTO_DESCRIPCION = "Deposito";
    public static BigDecimal VALOR = BigDecimal.valueOf(1000);

    public static RealizarMovimientoResponse REALIZAR_MOVIMIENTO_RESPONSE =
            RealizarMovimientoResponse.builder()
                    .numeroCuenta(NUMERO_CUENTA)
                    .tipoCuentaDescripcion(TIPO_CUENTA_DESCRIPCION)
                    .saldoInicial(SALDO_INICIAL)
                    .estado(Boolean.TRUE)
                    .tipoMovimientoDescripcion(TIPO_MOVIMIENTO_DESCRIPCION)
                    .valor(VALOR).build();



}
