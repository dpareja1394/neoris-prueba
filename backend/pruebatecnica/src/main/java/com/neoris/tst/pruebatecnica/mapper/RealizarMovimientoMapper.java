package com.neoris.tst.pruebatecnica.mapper;

import com.neoris.tst.pruebatecnica.domain.Cuenta;
import com.neoris.tst.pruebatecnica.domain.Movimiento;
import com.neoris.tst.pruebatecnica.domain.TipoMovimiento;
import com.neoris.tst.pruebatecnica.request.RealizarMovimientoRequest;
import com.neoris.tst.pruebatecnica.response.RealizarMovimientoResponse;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

public class RealizarMovimientoMapper {

    public static Movimiento requestToMovimiento(RealizarMovimientoRequest movimientoRequest
            , Cuenta cuenta, TipoMovimiento tipoMovimiento) {
        return Movimiento.builder()
                .cuenta(cuenta)
                .tipoMovimiento(tipoMovimiento)
                .fecha(Timestamp.from(Instant.now()))
                .valor(movimientoRequest.getValor())
                .saldo(cuenta.getSaldoActual())
                .estado(true)
                .build();
    }

    public static RealizarMovimientoResponse domainToResponse(Movimiento movimiento, BigDecimal saldoInicial) {
        return RealizarMovimientoResponse.builder()
                .numeroCuenta(movimiento.getCuenta().getNumeroCuenta())
                .tipoCuentaDescripcion(movimiento.getCuenta().getTipoCuenta().getDescripcion())
                .saldoInicial(saldoInicial)
                .estado(movimiento.getEstado())
                .tipoMovimientoDescripcion(movimiento.getTipoMovimiento().getDescripcion())
                .valor(movimiento.getValor())
                .build();
    }

}
