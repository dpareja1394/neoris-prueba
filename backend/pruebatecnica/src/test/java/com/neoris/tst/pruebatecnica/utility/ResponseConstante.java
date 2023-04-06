package com.neoris.tst.pruebatecnica.utility;

import com.neoris.tst.pruebatecnica.response.CrearUsuarioResponse;

public class ResponseConstante {

    public static CrearUsuarioResponse CREAR_USUARIO_RESPONSE_OK = CrearUsuarioResponse.builder()
            .nombres("Jose Lema")
            .direccion("Otavalo sn y principal")
            .telefono("098254785")
            .contrasena("1234")
            .estado(true)
            .build();
}
