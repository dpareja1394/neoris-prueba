package com.neoris.tst.pruebatecnica.service;

import com.neoris.tst.pruebatecnica.domain.Persona;
import com.neoris.tst.pruebatecnica.exception.PersonaException;
import com.neoris.tst.pruebatecnica.repository.PersonaRepository;
import org.springframework.stereotype.Service;

import static com.neoris.tst.pruebatecnica.utility.MensajeExcepcionService.*;

@Service
public class PersonaServiceImpl implements PersonaService{

    private final PersonaRepository personaRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public Persona guardarPersona(Persona persona) throws PersonaException {

        // Validar si la persona ya existe en base de datos;
        if(personaRepository.existsByIdentificacion(persona.getIdentificacion())) {
            throw new PersonaException(
                    String.format(PERSONA_EXISTE_POR_IDENTIFICACION_MENSAJE, persona.getIdentificacion()));
        }

        return personaRepository.save(persona);
    }

    @Override
    public Persona buscarPersonaPorNombreYEstado(String nombre, Boolean estado) throws PersonaException {
        return personaRepository.findByNombreAndEstado(nombre, estado).orElseThrow(
                () ->new PersonaException(
                        String.format(PERSONA_NO_EXISTE_POR_NOMBRE_MENSAJE,
                                nombre, estado))
        );
    }

    @Override
    public Persona buscarPersonaPorIdentificacion(String identificacion) throws PersonaException {
        return personaRepository.findByIdentificacion(identificacion).orElseThrow(
                () ->new PersonaException(
                        String.format(PERSONA_NO_EXISTE_POR_IDENTIFICACION_MENSAJE,
                                identificacion))
        );
    }

    @Override
    public Persona inactivarPersona(Persona persona) {
        persona.setEstado(false);
        return personaRepository.save(persona);
    }

    @Override
    public Persona activarPersona(Persona persona) {
        persona.setEstado(true);
        return personaRepository.save(persona);
    }
}
