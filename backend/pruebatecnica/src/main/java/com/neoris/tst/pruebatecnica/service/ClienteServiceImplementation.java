package com.neoris.tst.pruebatecnica.service;

import com.neoris.tst.pruebatecnica.request.CrearUsuarioRequest;
import com.neoris.tst.pruebatecnica.response.CrearUsuarioResponse;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImplementation implements ClienteService{
    @Override
    public CrearUsuarioResponse crearUsuario(CrearUsuarioRequest crearUsuarioRequest){

        return CrearUsuarioResponse.builder()
                .nombres(crearUsuarioRequest.getNombres()).build();
    }
}
