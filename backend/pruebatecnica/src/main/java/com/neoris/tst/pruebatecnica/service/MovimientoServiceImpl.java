package com.neoris.tst.pruebatecnica.service;

import com.neoris.tst.pruebatecnica.domain.Cuenta;
import com.neoris.tst.pruebatecnica.domain.Movimiento;
import com.neoris.tst.pruebatecnica.domain.TipoMovimiento;
import com.neoris.tst.pruebatecnica.exception.*;
import com.neoris.tst.pruebatecnica.mapper.RealizarMovimientoMapper;
import com.neoris.tst.pruebatecnica.repository.MovimientoRepository;
import com.neoris.tst.pruebatecnica.request.RealizarMovimientoRequest;
import com.neoris.tst.pruebatecnica.response.MovimientoPorFechaPorUsuarioResponse;
import com.neoris.tst.pruebatecnica.response.RealizarMovimientoResponse;
import com.neoris.tst.pruebatecnica.utility.Constante;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.neoris.tst.pruebatecnica.utility.MensajeExcepcionService.*;

@Service
public class MovimientoServiceImpl implements MovimientoService{

    private final MovimientoRepository movimientoRepository;
    private final CuentaService cuentaService;
    private final TipoMovimientoService tipoMovimientoService;

    public MovimientoServiceImpl(MovimientoRepository movimientoRepository,
                                 CuentaService cuentaService,
                                 TipoMovimientoService tipoMovimientoService) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaService = cuentaService;
        this.tipoMovimientoService = tipoMovimientoService;
    }

    @Override
    public RealizarMovimientoResponse realizarMovimiento(RealizarMovimientoRequest movimientoRequest)
            throws TipoCuentaException, CuentaException,
            TipoMovimientoException, MovimientoException {
        //1. Buscar la cuenta por número y tipo de cuenta
        Cuenta cuenta = cuentaService
                .buscarCuentaPorNumeroYTipoCuenta(movimientoRequest.getNumeroCuenta(),
                        movimientoRequest.getTipoCuentaDescripcion());

        if (!cuenta.getEstado()) {
            throw new CuentaException(MOVIMIENTO_ERROR_CUENTA_INACTIVA);
        }

        BigDecimal saldo = cuenta.getSaldoActual();

        //2. Validar si existe el tipo de movimiento
        TipoMovimiento tipoMovimiento = tipoMovimientoService
                .buscarPorDescripcionYEstado(movimientoRequest.getTipoMovimientoDescripcion(), true);

        //3. Validar si es retiro o consignación
        Movimiento movimiento;
        if(tipoMovimiento.getAbreviatura().trim().equals(Constante.TIPO_MOVIMIENTO_RETIRO)) {
            movimiento = efectuarRetiro(movimientoRequest, cuenta, tipoMovimiento);
        } else {
            movimiento = efectuarDeposito(movimientoRequest, cuenta, tipoMovimiento);
        }

        return RealizarMovimientoMapper.domainToResponse(movimiento, saldo);
    }

    private Movimiento efectuarRetiro
            (RealizarMovimientoRequest movimientoRequest, Cuenta cuenta, TipoMovimiento tipoMovimiento)
            throws MovimientoException {
        if(BigDecimal.ZERO.compareTo(cuenta.getSaldoActual()) == 0) {
            throw new MovimientoException(SALDO_NO_DISPONIBLE);
        }

        //4. Si es retiro validar que el valor a retirar no sea mayor al saldo de la cuenta
        if (movimientoRequest.getValor().compareTo(cuenta.getSaldoActual()) > 0) {
            throw new MovimientoException(RETIRO_EXCEDE_SALDO_CUENTA_MENSAJE);
        }

        Movimiento movimiento = RealizarMovimientoMapper.requestToMovimiento(movimientoRequest, cuenta, tipoMovimiento);
        BigDecimal saldoActualCuenta = cuenta.getSaldoActual();
        cuenta.setSaldoActual(saldoActualCuenta.subtract(movimiento.getValor()));

        cuentaService.efectuarMovimientoEnCuenta(cuenta);


        return movimientoRepository.save(movimiento);
    }

    private Movimiento efectuarDeposito
            (RealizarMovimientoRequest movimientoRequest, Cuenta cuenta, TipoMovimiento tipoMovimiento) {
        Movimiento movimiento = RealizarMovimientoMapper.requestToMovimiento(movimientoRequest, cuenta, tipoMovimiento);
        BigDecimal saldoActualCuenta = cuenta.getSaldoActual();
        cuenta.setSaldoActual(saldoActualCuenta.add(movimiento.getValor()));

        cuentaService.efectuarMovimientoEnCuenta(cuenta);

        return movimientoRepository.save(movimiento);
    }

    @Override
    public List<MovimientoPorFechaPorUsuarioResponse> buscarMovimientosEnLasCuentasDeUnCliente
            (String identificacion, LocalDateTime desde, LocalDateTime hasta) throws PersonaException, ClienteException {
        List<Integer> idsCuentas = cuentaService.
                consultarListadoCuentasPorUsuario(identificacion).
                stream().map(Cuenta::getId).toList();

        List<Movimiento> movimientos = movimientoRepository.findByCuentaIdInAndFechaBetween(idsCuentas, desde, hasta);
        //Crear mapper para movimiento

        return null;
    }
}
