package com.neoris.tst.pruebatecnica.utility.service;

import com.neoris.tst.pruebatecnica.domain.Genero;

import java.util.Arrays;
import java.util.List;

public class GeneroConstante {

    public static Genero GENERO_MASCULINO = Genero.builder()
            .id(1)
            .abreviatura("M")
            .estado(true)
            .descripcion("Masculino")
            .build();

    public static Genero GENERO_FEMENINO = Genero.builder()
            .id(2)
            .abreviatura("F")
            .estado(true)
            .descripcion("Femenino")
            .build();

    public static List<Genero> GENEROS = Arrays.asList(GENERO_MASCULINO, GENERO_FEMENINO);

}
