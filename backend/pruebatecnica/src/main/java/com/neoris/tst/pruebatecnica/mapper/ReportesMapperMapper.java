package com.neoris.tst.pruebatecnica.mapper;

import com.neoris.tst.pruebatecnica.domain.Movimiento;
import com.neoris.tst.pruebatecnica.response.MovimientoPorFechaPorCuentaResponse;
import com.neoris.tst.pruebatecnica.response.MovimientoPorFechaPorUsuarioResponse;
import com.neoris.tst.pruebatecnica.utility.Constante;

import java.math.BigDecimal;
import java.util.List;

public class ReportesMapperMapper {

    public static MovimientoPorFechaPorUsuarioResponse domainToMovimientoPorFechaPorUsuarioResponse(Movimiento movimiento) {
        return MovimientoPorFechaPorUsuarioResponse.builder()
                .fecha(movimiento.getFecha())
                .cliente(movimiento.getCuenta().getCliente().getPersona().getNombre())
                .numeroCuenta(movimiento.getCuenta().getNumeroCuenta())
                .tipoCuenta(movimiento.getCuenta().getTipoCuenta().getDescripcion())
                .saldoInicial(movimiento.getSaldo())
                .estado(movimiento.getEstado())
                .movimiento(movimiento.getTipoMovimiento().
                        getAbreviatura().equalsIgnoreCase(Constante.TIPO_MOVIMIENTO_RETIRO) ?
                        movimiento.getValor().multiply(BigDecimal.valueOf(-1)) : movimiento.getValor())
                .saldoDisponible(movimiento.getTipoMovimiento().
                        getAbreviatura().equalsIgnoreCase(Constante.TIPO_MOVIMIENTO_RETIRO) ?
                        movimiento.getSaldo().subtract(movimiento.getValor()) :
                        movimiento.getSaldo().add(movimiento.getValor())
                        )
                .build();
    }

    public static List<MovimientoPorFechaPorUsuarioResponse> domainToMovimientoPorFechaPorUsuarioResponseList(List<Movimiento> movimientos) {
        return movimientos.stream().map(ReportesMapperMapper::domainToMovimientoPorFechaPorUsuarioResponse).toList();
    }

    public static MovimientoPorFechaPorCuentaResponse domainToMovimientoPorFechaPorCuentaResponse(Movimiento movimiento) {
        return MovimientoPorFechaPorCuentaResponse.builder()
                .fecha(movimiento.getFecha())
                .numeroCuenta(movimiento.getCuenta().getNumeroCuenta())
                .tipoCuenta(movimiento.getCuenta().getTipoCuenta().getDescripcion())
                .saldoInicial(movimiento.getSaldo())
                .estado(movimiento.getEstado())
                .movimiento(movimiento.getTipoMovimiento().
                        getAbreviatura().equalsIgnoreCase(Constante.TIPO_MOVIMIENTO_RETIRO) ?
                        movimiento.getValor().multiply(BigDecimal.valueOf(-1)) : movimiento.getValor())
                .saldoDisponible(movimiento.getTipoMovimiento().
                        getAbreviatura().equalsIgnoreCase(Constante.TIPO_MOVIMIENTO_RETIRO) ?
                        movimiento.getSaldo().subtract(movimiento.getValor()) :
                        movimiento.getSaldo().add(movimiento.getValor())
                )
                .build();
    }

    public static List<MovimientoPorFechaPorCuentaResponse> domainToMovimientoPorFechaPorCuentaResponseList(List<Movimiento> movimientos) {
        return movimientos.stream().map(ReportesMapperMapper::domainToMovimientoPorFechaPorCuentaResponse).toList();
    }

}
