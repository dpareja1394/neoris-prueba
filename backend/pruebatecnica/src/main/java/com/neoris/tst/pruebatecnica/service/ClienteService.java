package com.neoris.tst.pruebatecnica.service;

import com.neoris.tst.pruebatecnica.request.CrearUsuarioRequest;
import com.neoris.tst.pruebatecnica.response.CrearUsuarioResponse;
import org.springframework.stereotype.Service;


public interface ClienteService {

    CrearUsuarioResponse crearUsuario(CrearUsuarioRequest crearUsuarioRequest);

}
