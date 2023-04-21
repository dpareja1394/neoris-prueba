package com.neoris.tst.pruebatecnica.service;

import com.neoris.tst.pruebatecnica.domain.Persona;
import com.neoris.tst.pruebatecnica.repository.PersonaRepository;
import com.neoris.tst.pruebatecnica.service.implementation.PersonaServiceImpl;
import com.neoris.tst.pruebatecnica.utility.service.PersonaConstante;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class PersonaServiceImplTest {

    @InjectMocks
    private PersonaServiceImpl personaService;

    @Mock
    private PersonaRepository personaRepository;

    @Test
    public void inactivarPersona() {
        given(personaRepository.save(PersonaConstante.PERSONA)).willReturn(PersonaConstante.PERSONA);
        Persona persona = personaRepository.save(PersonaConstante.PERSONA);
        persona = personaService.inactivarPersona(persona);
        assertFalse(persona.getEstado());
    }

    @Test
    public void activarPersona() {
        given(personaRepository.save(PersonaConstante.PERSONA_INACTIVA)).willReturn(PersonaConstante.PERSONA_INACTIVA);
        Persona persona = personaRepository.save(PersonaConstante.PERSONA_INACTIVA);
        persona = personaService.activarPersona(persona);
        assertTrue(persona.getEstado());
    }
}
