package com.neoris.tst.pruebatecnica.mapper;

import com.neoris.tst.pruebatecnica.domain.Persona;
import com.neoris.tst.pruebatecnica.request.CrearUsuarioRequest;

public class CrearUsuarioRequestToPersonaMapper {

    public static Persona requestToDomain(CrearUsuarioRequest usuarioRequest) {
        return Persona.builder()
                .nombre(usuarioRequest.getNombres())
                .edad(usuarioRequest.getEdad())
                .identificacion(usuarioRequest.getIdentificacion())
                .direccion(usuarioRequest.getDireccion())
                .telefono(usuarioRequest.getTelefono())
                .estado(usuarioRequest.getEstado())
                .build();
    }

}
