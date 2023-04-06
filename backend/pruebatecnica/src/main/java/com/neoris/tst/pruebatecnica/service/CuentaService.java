package com.neoris.tst.pruebatecnica.service;

import com.neoris.tst.pruebatecnica.exception.ClienteNoExistePorIdentificacion;
import com.neoris.tst.pruebatecnica.exception.CuentaExistePorClienteTipoCuentaEstado;
import com.neoris.tst.pruebatecnica.exception.PersonaNoExistePorNombre;
import com.neoris.tst.pruebatecnica.exception.TipoCuentaNoExistePorDescripcion;
import com.neoris.tst.pruebatecnica.request.CrearCuentaUsuarioRequest;
import com.neoris.tst.pruebatecnica.response.CrearCuentaUsuarioResponse;

public interface CuentaService {

    CrearCuentaUsuarioResponse crearCuentaUsuario(CrearCuentaUsuarioRequest crearCuentaUsuarioRequest) throws ClienteNoExistePorIdentificacion, PersonaNoExistePorNombre, TipoCuentaNoExistePorDescripcion, CuentaExistePorClienteTipoCuentaEstado;

}
