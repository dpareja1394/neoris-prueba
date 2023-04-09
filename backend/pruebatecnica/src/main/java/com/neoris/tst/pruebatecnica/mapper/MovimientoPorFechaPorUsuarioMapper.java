package com.neoris.tst.pruebatecnica.mapper;

import com.neoris.tst.pruebatecnica.domain.Movimiento;
import com.neoris.tst.pruebatecnica.response.MovimientoPorFechaPorUsuarioResponse;
import com.neoris.tst.pruebatecnica.utility.Constante;

import java.math.BigDecimal;
import java.util.List;

public class MovimientoPorFechaPorUsuarioMapper {

    public static MovimientoPorFechaPorUsuarioResponse domainToResponse(Movimiento movimiento) {
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

    public static List<MovimientoPorFechaPorUsuarioResponse> domainToResponseList(List<Movimiento> movimientos) {
        return movimientos.stream().map(MovimientoPorFechaPorUsuarioMapper::domainToResponse).toList();
    }

}
