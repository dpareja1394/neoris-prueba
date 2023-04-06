package com.neoris.tst.pruebatecnica.service;

import com.neoris.tst.pruebatecnica.domain.Persona;
import com.neoris.tst.pruebatecnica.exception.PersonaExiste;

public interface PersonaService {
    Persona savePersona(Persona persona) throws PersonaExiste;

}
