package com.neoris.tst.pruebatecnica.service;

import com.neoris.tst.pruebatecnica.exception.*;
import com.neoris.tst.pruebatecnica.request.RealizarMovimientoRequest;
import com.neoris.tst.pruebatecnica.response.MovimientoPorFechaPorUsuarioResponse;
import com.neoris.tst.pruebatecnica.response.RealizarMovimientoResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface MovimientoService {

    RealizarMovimientoResponse realizarMovimiento(RealizarMovimientoRequest movimientoRequest)
            throws TipoCuentaException, CuentaException,
            TipoMovimientoException, MovimientoException;

    List<MovimientoPorFechaPorUsuarioResponse> buscarMovimientosEnLasCuentasDeUnCliente
            (String identificacion, LocalDateTime desde, LocalDateTime hasta) throws PersonaException, ClienteException;

}
