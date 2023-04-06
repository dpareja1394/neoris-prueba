package com.neoris.tst.pruebatecnica.service;

import com.neoris.tst.pruebatecnica.domain.Persona;
import com.neoris.tst.pruebatecnica.exception.PersonaExistePorIdentificacion;
import com.neoris.tst.pruebatecnica.exception.PersonaNoExistePorNombre;
import com.neoris.tst.pruebatecnica.repository.PersonaRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService{

    private final PersonaRepository personaRepository;

    public final static String PERSONA_EXISTE_POR_IDENTIFICACION_MENSAJE =
            "La persona con identificación %s y estado %s ya existe en el sistema";
    public final static String PERSONA_NO_EXISTE_POR_NOMBRE_MENSAJE =
            "%s y estado %s no existe como cliente";

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public Persona guardarPersona(Persona persona) throws PersonaExistePorIdentificacion {

        // Validar si la persona ya existe en base de datos;
        if(personaRepository.existsByIdentificacionAndEstado(persona.getIdentificacion(), persona.getEstado())) {
            throw new PersonaExistePorIdentificacion(
                    String.format(PERSONA_EXISTE_POR_IDENTIFICACION_MENSAJE, persona.getIdentificacion(), persona.getEstado()));
        }

        return personaRepository.save(persona);
    }

    @Override
    public Persona buscarPersonaPorNombreYEstado(String nombre, Boolean estado) throws PersonaNoExistePorNombre {
        return personaRepository.findByNombreAndEstado(nombre, estado).orElseThrow(
                () ->new PersonaNoExistePorNombre(
                        String.format(PERSONA_EXISTE_POR_IDENTIFICACION_MENSAJE,
                                nombre, estado))
        );
    }
}
