package com.neoris.tst.pruebatecnica.utility.service;

import com.neoris.tst.pruebatecnica.domain.Persona;

public class PersonaConstante {

    public static Persona PERSONA = Persona.builder()
            .id(1)
            .genero(GeneroConstante.GENERO_MASCULINO)
            .nombre("Juan")
            .edad((short) 28)
            .identificacion("101234")
            .telefono("5555555")
            .direccion("Calle Carrera")
            .estado(true)
            .build();

    public static Persona PERSONA_INACTIVA = Persona.builder()
            .id(1)
            .genero(GeneroConstante.GENERO_MASCULINO)
            .nombre("Juan")
            .edad((short) 28)
            .identificacion("101234")
            .telefono("5555555")
            .direccion("Calle Carrera")
            .estado(false)
            .build();

}
