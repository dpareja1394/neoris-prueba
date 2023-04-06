package com.neoris.tst.pruebatecnica.service;

import com.neoris.tst.pruebatecnica.domain.TipoCuenta;
import com.neoris.tst.pruebatecnica.exception.TipoCuentaNoExistePorDescripcion;

public interface TipoCuentaService {
    TipoCuenta buscarTipoCuentaPorDescripcionYEstado(String descripcion, Boolean estado) throws TipoCuentaNoExistePorDescripcion;

}
