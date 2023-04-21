package com.neoris.tst.pruebatecnica.utility.controller;

import com.neoris.tst.pruebatecnica.request.ActivarUsuarioRequest;
import com.neoris.tst.pruebatecnica.request.CrearUsuarioRequest;
import com.neoris.tst.pruebatecnica.request.InactivarUsuarioRequest;
import com.neoris.tst.pruebatecnica.request.ModificarUsuarioRequest;

public class ClienteRequestConstante {

    public static CrearUsuarioRequest CREAR_USUARIO_REQUEST_OK = CrearUsuarioRequest.builder()
            .nombres("Jose Lema")
            .identificacion("101234")
            .edad((short) 28)
            .direccion("Otavalo sn y principal")
            .telefono("098254785")
            .contrasena("1234")
            .genero("M")
            .build();

    public static CrearUsuarioRequest CREAR_USUARIO_NOMBRE_NULL = CrearUsuarioRequest.builder()
            .identificacion("101234")
            .edad((short) 28)
            .direccion("Otavalo sn y principal")
            .telefono("098254785")
            .contrasena("1234")
            .genero("M")
            .build();

    public static ModificarUsuarioRequest MODIFICAR_USUARIO_REQUEST_OK = ModificarUsuarioRequest.builder()
            .identificacion("101234")
            .nombres("Jose Lema")
            .edad((short) 28)
            .direccion("Otavalo sn y principal")
            .telefono("098254785")
            .contrasena("1234")
            .genero("M")
            .build();

    public static ModificarUsuarioRequest MODIFICAR_USUARIO_IDENTIFICACION_NULL = ModificarUsuarioRequest.builder()
            .nombres("Jose Lema")
            .edad((short) 28)
            .direccion("Otavalo sn y principal")
            .telefono("098254785")
            .contrasena("1234")
            .genero("M")
            .build();

    public static InactivarUsuarioRequest INACTIVAR_USUARIO_REQUEST_OK = InactivarUsuarioRequest.builder()
            .identificacion("101234")
            .build();

    public static InactivarUsuarioRequest INACTIVAR_USUARIO_REQUEST_IDENTIFICACION_NULL =
            InactivarUsuarioRequest.builder().build();

    public static ActivarUsuarioRequest ACTIVAR_USUARIO_REQUEST_OK = ActivarUsuarioRequest.builder()
            .identificacion("101234")
            .build();

    public static ActivarUsuarioRequest ACTIVAR_USUARIO_REQUEST_IDENTIFICACION_NULL =
            ActivarUsuarioRequest.builder().build();
}
