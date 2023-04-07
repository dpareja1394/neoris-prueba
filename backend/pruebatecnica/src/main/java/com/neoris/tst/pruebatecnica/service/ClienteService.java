package com.neoris.tst.pruebatecnica.service;

import com.neoris.tst.pruebatecnica.domain.Cliente;
import com.neoris.tst.pruebatecnica.exception.ClienteException;
import com.neoris.tst.pruebatecnica.exception.GeneroException;
import com.neoris.tst.pruebatecnica.exception.PersonaException;
import com.neoris.tst.pruebatecnica.request.CrearUsuarioRequest;
import com.neoris.tst.pruebatecnica.request.InactivarUsuarioRequest;
import com.neoris.tst.pruebatecnica.response.CrearUsuarioResponse;
import com.neoris.tst.pruebatecnica.response.InactivarUsuarioResponse;


public interface ClienteService {

    CrearUsuarioResponse crearUsuario(CrearUsuarioRequest crearUsuarioRequest)
            throws GeneroException, PersonaException;

    Cliente buscarClientePorNombreYEstado(String nombre, boolean estado)
            throws PersonaException, ClienteException;

    InactivarUsuarioResponse inactivarUsuario(InactivarUsuarioRequest inactivarUsuarioRequest) throws PersonaException, ClienteException;
}
