package com.neoris.tst.pruebatecnica.utility.controller;

import com.neoris.tst.pruebatecnica.domain.Cliente;
import com.neoris.tst.pruebatecnica.domain.Genero;
import com.neoris.tst.pruebatecnica.domain.Persona;
import com.neoris.tst.pruebatecnica.response.*;

import java.util.Arrays;
import java.util.List;

public class ClienteResponseConstante {

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

    public static Genero GENERO = Genero.builder()
            .id(1)
            .abreviatura("M")
            .descripcion("Masculino")
            .estado(true)
            .build();

    public static Persona PERSONA = Persona.builder()
            .id(1)
            .genero(GENERO)
            .nombre("Persona 1")
            .edad((short) 30)
            .identificacion("111111")
            .direccion("Calle Carrera")
            .telefono("12345")
            .estado(true)
            .build();

    public static Cliente CLIENTE = Cliente.builder()
            .id(1)
            .persona(PERSONA)
            .contrasena("12345")
            .estado(true)
            .build();

    public static BuscarUsuarioResponse BUSCAR_USUARIO = BuscarUsuarioResponse.builder()
            .identificacion(ClienteResponseConstante.PERSONA.getIdentificacion())
            .nombres(ClienteResponseConstante.PERSONA.getNombre())
            .direccion(ClienteResponseConstante.PERSONA.getDireccion())
            .telefono(ClienteResponseConstante.PERSONA.getTelefono())
            .contrasena(ClienteResponseConstante.CLIENTE.getContrasena())
            .generoDescripcion(ClienteResponseConstante.PERSONA.getGenero().getDescripcion())
            .estado(ClienteResponseConstante.PERSONA.getEstado())
            .build();

    public static List<BuscarUsuarioResponse> BUSCAR_USUARIOS =
            Arrays.asList(
                    BuscarUsuarioResponse.builder().identificacion("101234").nombres("Jose Lema")
                        .direccion("Otavalo sn y principal").telefono("098254785").contrasena("1234")
                            .generoDescripcion("Masculino").estado(true).build(),
                    BuscarUsuarioResponse.builder().identificacion("201234").nombres("Marianela Montalvo")
                            .direccion("Marianela Montalvo").telefono("097548965").contrasena("5678")
                            .generoDescripcion("Femenino").estado(true).build()
            );

}
