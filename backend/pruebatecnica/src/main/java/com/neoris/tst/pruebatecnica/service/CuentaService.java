package com.neoris.tst.pruebatecnica.service;

import com.neoris.tst.pruebatecnica.domain.Cuenta;
import com.neoris.tst.pruebatecnica.exception.*;
import com.neoris.tst.pruebatecnica.request.CrearCuentaUsuarioRequest;
import com.neoris.tst.pruebatecnica.response.CrearCuentaUsuarioResponse;

public interface CuentaService {

    CrearCuentaUsuarioResponse crearCuentaUsuario(CrearCuentaUsuarioRequest crearCuentaUsuarioRequest)
            throws ClienteNoExistePorIdentificacion, PersonaNoExistePorNombre,
            TipoCuentaNoExistePorDescripcion, CuentaExistePorClienteTipoCuentaEstado, ClienteNoExistePorNombreYEstado;

    Cuenta buscarCuentaPorNumeroYTipoCuenta(String numeroCuenta, String tipoCuentaDescripcion)
            throws TipoCuentaNoExistePorDescripcion, CuentaNoExistePorNumeroTipoCuentaEstado;

    Cuenta efectuarMovimientoEnCuenta(Cuenta cuenta);

}
