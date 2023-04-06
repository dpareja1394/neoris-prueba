package com.neoris.tst.pruebatecnica.service;

import com.neoris.tst.pruebatecnica.domain.Persona;
import com.neoris.tst.pruebatecnica.exception.PersonaExistePorIdentificacion;
import com.neoris.tst.pruebatecnica.exception.PersonaNoExistePorNombre;

public interface PersonaService {
    Persona guardarPersona(Persona persona) throws PersonaExistePorIdentificacion;
    Persona buscarPersonaPorNombreYEstado(String nombre, Boolean estado) throws PersonaNoExistePorNombre;

}
