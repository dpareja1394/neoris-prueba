package com.neoris.tst.pruebatecnica.mapper;

import com.neoris.tst.pruebatecnica.domain.Cliente;
import com.neoris.tst.pruebatecnica.request.CrearUsuarioRequest;

public class CrearUsuarioRequestToClienteMapper {

    public static Cliente requestToDomain(CrearUsuarioRequest usuarioRequest) {
        return Cliente.builder()
                .contrasena(usuarioRequest.getContrasena())
                .estado(usuarioRequest.getEstado())
                .build();
    }

}
