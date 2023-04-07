package com.neoris.tst.pruebatecnica.service;

import com.neoris.tst.pruebatecnica.domain.Cliente;
import com.neoris.tst.pruebatecnica.exception.*;
import com.neoris.tst.pruebatecnica.request.CrearUsuarioRequest;
import com.neoris.tst.pruebatecnica.request.InactivarUsuarioRequest;
import com.neoris.tst.pruebatecnica.response.CrearUsuarioResponse;
import com.neoris.tst.pruebatecnica.response.InactivarUsuarioResponse;


public interface ClienteService {

    CrearUsuarioResponse crearUsuario(CrearUsuarioRequest crearUsuarioRequest)
            throws GeneroNoEncontradoPorAbreviatura, PersonaExistePorIdentificacion;

    Cliente buscarClientePorNombreYEstado(String nombre, boolean estado) throws PersonaNoExistePorNombre, ClienteNoExistePorNombreYEstado;

    InactivarUsuarioResponse inactivarUsuario(InactivarUsuarioRequest inactivarUsuarioRequest);
}
