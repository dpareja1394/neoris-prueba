package com.neoris.tst.pruebatecnica.service;

import com.neoris.tst.pruebatecnica.domain.Cuenta;
import com.neoris.tst.pruebatecnica.domain.TipoCuenta;
import com.neoris.tst.pruebatecnica.exception.ClienteException;
import com.neoris.tst.pruebatecnica.exception.CuentaException;
import com.neoris.tst.pruebatecnica.exception.PersonaException;
import com.neoris.tst.pruebatecnica.exception.TipoCuentaException;
import com.neoris.tst.pruebatecnica.request.CrearCuentaUsuarioRequest;
import com.neoris.tst.pruebatecnica.response.CrearCuentaUsuarioResponse;

public interface CuentaService {

    CrearCuentaUsuarioResponse crearCuentaUsuario(CrearCuentaUsuarioRequest crearCuentaUsuarioRequest)
            throws ClienteException, PersonaException,
            TipoCuentaException, CuentaException;

    Cuenta buscarCuentaPorNumeroYTipoCuenta(String numeroCuenta, String tipoCuentaDescripcion)
            throws TipoCuentaException, CuentaException;

    Cuenta efectuarMovimientoEnCuenta(Cuenta cuenta);

}
