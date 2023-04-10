package com.neoris.tst.pruebatecnica.service;

import com.neoris.tst.pruebatecnica.domain.TipoMovimiento;
import com.neoris.tst.pruebatecnica.exception.TipoMovimientoException;

public interface TipoMovimientoService {
    TipoMovimiento buscarPorDescripcionYEstado(String descripcion, Boolean estado)
            throws TipoMovimientoException;

}
