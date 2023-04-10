package com.neoris.tst.pruebatecnica.utility;

import com.neoris.tst.pruebatecnica.response.ActivarUsuarioResponse;
import com.neoris.tst.pruebatecnica.response.CrearUsuarioResponse;
import com.neoris.tst.pruebatecnica.response.InactivarUsuarioResponse;
import com.neoris.tst.pruebatecnica.response.ModificarUsuarioResponse;

public class ResponseConstante {

    public static CrearUsuarioResponse CREAR_USUARIO_RESPONSE_OK = CrearUsuarioResponse.builder()
            .nombres("Jose Lema")
            .direccion("Otavalo sn y principal")
            .telefono("098254785")
            .contrasena("1234")
            .estado(true)
            .build();

    public static ModificarUsuarioResponse MODIFICAR_USUARIO_RESPONSE_OK = ModificarUsuarioResponse.builder()
            .identificacion("101234")
            .nombres("Jose Lema")
            .direccion("Otavalo sn y principal")
            .telefono("098254785")
            .contrasena("1234")
            .generoDescripcion("Masculino")
            .estado(true)
            .build();

    public static InactivarUsuarioResponse INACTIVAR_USUARIO_RESPONSE_OK = InactivarUsuarioResponse.builder()
            .identificacion("101234")
            .nombre("Jose Lema")
            .estado(false)
            .build();

    public static ActivarUsuarioResponse ACTIVAR_USUARIO_RESPONSE_OK = ActivarUsuarioResponse.builder()
            .identificacion("101234")
            .nombre("Jose Lema")
            .estado(true)
            .build();


}
