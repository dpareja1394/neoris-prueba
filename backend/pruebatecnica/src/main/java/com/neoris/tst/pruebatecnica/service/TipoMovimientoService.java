package com.neoris.tst.pruebatecnica.service;

import com.neoris.tst.pruebatecnica.domain.TipoCuenta;
import com.neoris.tst.pruebatecnica.domain.TipoMovimiento;
import com.neoris.tst.pruebatecnica.exception.TipoCuentaNoExistePorDescripcion;
import com.neoris.tst.pruebatecnica.exception.TipoMovimientoNoExistePorDescripcion;

public interface TipoMovimientoService {
    TipoMovimiento buscarPorDescripcionYEstado(String descripcion, Boolean estado) throws TipoMovimientoNoExistePorDescripcion;

}
