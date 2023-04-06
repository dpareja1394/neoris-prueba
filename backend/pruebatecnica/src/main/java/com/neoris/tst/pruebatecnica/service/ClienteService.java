package com.neoris.tst.pruebatecnica.service;

import com.neoris.tst.pruebatecnica.exception.GeneroNoEncontradoPorAbreviatura;
import com.neoris.tst.pruebatecnica.exception.PersonaExistePorIdentificacion;
import com.neoris.tst.pruebatecnica.request.CrearUsuarioRequest;
import com.neoris.tst.pruebatecnica.response.CrearUsuarioResponse;


public interface ClienteService {

    CrearUsuarioResponse crearUsuario(CrearUsuarioRequest crearUsuarioRequest)
            throws GeneroNoEncontradoPorAbreviatura, PersonaExistePorIdentificacion;

}
