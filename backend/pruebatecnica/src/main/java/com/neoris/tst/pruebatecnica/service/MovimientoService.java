package com.neoris.tst.pruebatecnica.service;

import com.neoris.tst.pruebatecnica.exception.CuentaException;
import com.neoris.tst.pruebatecnica.exception.MovimientoException;
import com.neoris.tst.pruebatecnica.exception.TipoCuentaException;
import com.neoris.tst.pruebatecnica.exception.TipoMovimientoException;
import com.neoris.tst.pruebatecnica.request.RealizarMovimientoRequest;
import com.neoris.tst.pruebatecnica.response.RealizarMovimientoResponse;

public interface MovimientoService {

    RealizarMovimientoResponse realizarMovimiento(RealizarMovimientoRequest movimientoRequest)
            throws TipoCuentaException, CuentaException,
            TipoMovimientoException, MovimientoException;

}
