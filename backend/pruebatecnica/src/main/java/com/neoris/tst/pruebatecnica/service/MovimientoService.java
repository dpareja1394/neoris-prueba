package com.neoris.tst.pruebatecnica.service;

import com.neoris.tst.pruebatecnica.exception.CuentaNoExistePorNumeroTipoCuentaEstado;
import com.neoris.tst.pruebatecnica.exception.RetiroExcedeSaldoCuenta;
import com.neoris.tst.pruebatecnica.exception.TipoCuentaNoExistePorDescripcion;
import com.neoris.tst.pruebatecnica.exception.TipoMovimientoNoExistePorDescripcion;
import com.neoris.tst.pruebatecnica.request.RealizarMovimientoRequest;
import com.neoris.tst.pruebatecnica.response.RealizarMovimientoResponse;

public interface MovimientoService {

    RealizarMovimientoResponse realizarMovimiento(RealizarMovimientoRequest movimientoRequest)
            throws TipoCuentaNoExistePorDescripcion, CuentaNoExistePorNumeroTipoCuentaEstado,
            TipoMovimientoNoExistePorDescripcion, RetiroExcedeSaldoCuenta;

}
