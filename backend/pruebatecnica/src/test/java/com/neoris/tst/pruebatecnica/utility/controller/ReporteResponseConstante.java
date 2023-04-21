package com.neoris.tst.pruebatecnica.utility.controller;

import com.neoris.tst.pruebatecnica.response.MovimientoPorFechaPorCuentaResponse;
import com.neoris.tst.pruebatecnica.response.MovimientoPorFechaPorUsuarioResponse;
import com.neoris.tst.pruebatecnica.utility.FechaUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ReporteResponseConstante {

    public static LocalDate FECHA = LocalDate.parse("2022-10-12");
    public static Date FECHA_DATE = FechaUtil.fecha(FECHA);
    public static String CLIENTE = "Jose Lema";
    public static String NUMERO_CUENTA = "478758";
    public static String TIPO_CUENTA_DESCRIPCION = "Ahorros";
    public static BigDecimal SALDO_INICIAL = BigDecimal.ZERO;
    public static BigDecimal MOVIMIENTO = BigDecimal.valueOf(1000);
    public static BigDecimal SALDO_DISPONIBLE = SALDO_INICIAL.add(MOVIMIENTO);

    public static List<MovimientoPorFechaPorUsuarioResponse> MOVIMIENTOS_POR_FECHA_POR_USUARIO =
            Arrays.asList(
                    MovimientoPorFechaPorUsuarioResponse.builder().fecha(FECHA_DATE)
                            .cliente(CLIENTE).numeroCuenta(NUMERO_CUENTA).tipoCuenta(TIPO_CUENTA_DESCRIPCION)
                            .saldoInicial(SALDO_INICIAL).estado(Boolean.TRUE).movimiento(MOVIMIENTO)
                            .saldoDisponible(SALDO_DISPONIBLE).build(),
                    MovimientoPorFechaPorUsuarioResponse.builder().fecha(FECHA_DATE)
                            .cliente(CLIENTE).numeroCuenta(NUMERO_CUENTA).tipoCuenta(TIPO_CUENTA_DESCRIPCION)
                            .saldoInicial(BigDecimal.valueOf(1000)).estado(Boolean.TRUE).movimiento(BigDecimal.valueOf(-500))
                            .saldoDisponible(BigDecimal.valueOf(500)).build()
            );

    public static List<MovimientoPorFechaPorCuentaResponse> MOVIMIENTOS_POR_FECHA_POR_CUENTA =
            Arrays.asList(
                    MovimientoPorFechaPorCuentaResponse.builder().fecha(FECHA_DATE)
                            .numeroCuenta(NUMERO_CUENTA).tipoCuenta(TIPO_CUENTA_DESCRIPCION)
                            .saldoInicial(SALDO_INICIAL).estado(Boolean.TRUE).movimiento(MOVIMIENTO)
                            .saldoDisponible(SALDO_DISPONIBLE).build(),
                    MovimientoPorFechaPorCuentaResponse.builder().fecha(FECHA_DATE)
                            .numeroCuenta(NUMERO_CUENTA).tipoCuenta(TIPO_CUENTA_DESCRIPCION)
                            .saldoInicial(BigDecimal.valueOf(1000)).estado(Boolean.TRUE).movimiento(BigDecimal.valueOf(-500))
                            .saldoDisponible(BigDecimal.valueOf(500)).build()
            );



}
