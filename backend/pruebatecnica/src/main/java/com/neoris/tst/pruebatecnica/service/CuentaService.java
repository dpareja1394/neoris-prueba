package com.neoris.tst.pruebatecnica.service;

import com.neoris.tst.pruebatecnica.domain.Cuenta;
import com.neoris.tst.pruebatecnica.exception.ClienteException;
import com.neoris.tst.pruebatecnica.exception.CuentaException;
import com.neoris.tst.pruebatecnica.exception.PersonaException;
import com.neoris.tst.pruebatecnica.exception.TipoCuentaException;
import com.neoris.tst.pruebatecnica.request.ActivarCuentaRequest;
import com.neoris.tst.pruebatecnica.request.CrearCuentaUsuarioRequest;
import com.neoris.tst.pruebatecnica.request.InactivarCuentaRequest;
import com.neoris.tst.pruebatecnica.response.ActivarCuentaResponse;
import com.neoris.tst.pruebatecnica.response.BuscarCuentaResponse;
import com.neoris.tst.pruebatecnica.response.CrearCuentaUsuarioResponse;
import com.neoris.tst.pruebatecnica.response.InactivarCuentaResponse;

import java.util.List;

public interface CuentaService {

    CrearCuentaUsuarioResponse crearCuentaUsuario(CrearCuentaUsuarioRequest crearCuentaUsuarioRequest)
            throws ClienteException, PersonaException,
            TipoCuentaException, CuentaException;

    Cuenta buscarCuentaPorNumeroYTipoCuenta(String numeroCuenta, String tipoCuentaDescripcion)
            throws TipoCuentaException, CuentaException;

    Cuenta efectuarMovimientoEnCuenta(Cuenta cuenta);

    BuscarCuentaResponse consultarCuentaPorNumeroYTipoCuenta(String numeroCuenta, String tipoCuentaDescripcion)
            throws CuentaException, TipoCuentaException;

    List<BuscarCuentaResponse> consultarCuentasPorUsuario(String identificacion)
            throws CuentaException, TipoCuentaException, PersonaException, ClienteException;

    InactivarCuentaResponse inactivarCuenta(InactivarCuentaRequest inactivarCuentaRequest)
            throws CuentaException, TipoCuentaException, PersonaException, ClienteException;

    ActivarCuentaResponse activarCuenta(ActivarCuentaRequest activarCuentaRequest)
            throws CuentaException, TipoCuentaException, PersonaException, ClienteException;

    String eliminarCuenta(String numeroCuenta, String tipoCuenta, boolean force) throws CuentaException, TipoCuentaException;

    List<Cuenta> consultarListadoCuentasPorUsuario(String identificacion) throws PersonaException, ClienteException;

    public Cuenta consultarYValidarCuenta(String numeroCuenta, String tipoCuentaDescripcion) throws CuentaException, TipoCuentaException;

}
