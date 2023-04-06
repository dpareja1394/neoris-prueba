package com.neoris.tst.pruebatecnica.service;

import com.neoris.tst.pruebatecnica.domain.Persona;
import com.neoris.tst.pruebatecnica.exception.PersonaExiste;
import com.neoris.tst.pruebatecnica.repository.PersonaRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService{

    private final PersonaRepository personaRepository;

    public final static String PERSONA_EXISTE_MENSAJE = "La persona con identificación %s y estado %s ya existe en el sistema";

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public Persona savePersona(Persona persona) throws PersonaExiste {

        // Validar si la persona ya existe en base de datos;
        if(personaRepository.existsByIdentificacionAndEstado(persona.getIdentificacion(), persona.getEstado())) {
            throw new PersonaExiste(
                    String.format(PERSONA_EXISTE_MENSAJE, persona.getIdentificacion(), persona.getEstado()));
        }


        return personaRepository.save(persona);
    }
}
