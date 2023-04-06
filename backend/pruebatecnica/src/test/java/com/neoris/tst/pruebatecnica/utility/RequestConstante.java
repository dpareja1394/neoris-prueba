package com.neoris.tst.pruebatecnica.utility;

import com.neoris.tst.pruebatecnica.request.CrearUsuarioRequest;
import com.neoris.tst.pruebatecnica.response.CrearUsuarioResponse;

public class RequestConstante {

    public static CrearUsuarioRequest CREAR_USUARIO_REQUEST_OK = CrearUsuarioRequest.builder()
            .nombres("Jose Lema")
            .identificacion("101234")
            .edad((short) 28)
            .direccion("Otavalo sn y principal")
            .telefono("098254785")
            .contrasena("1234")
            .genero("M")
            .estado(true)
            .build();

    public static CrearUsuarioRequest CREAR_USUARIO_NOMBRE_NULL = CrearUsuarioRequest.builder()
            .identificacion("101234")
            .edad((short) 28)
            .direccion("Otavalo sn y principal")
            .telefono("098254785")
            .contrasena("1234")
            .genero("M")
            .estado(true)
            .build();

}
