package com.neoris.tst.pruebatecnica.service;

import com.neoris.tst.pruebatecnica.domain.Cliente;
import com.neoris.tst.pruebatecnica.domain.Genero;
import com.neoris.tst.pruebatecnica.domain.Persona;
import com.neoris.tst.pruebatecnica.exception.*;
import com.neoris.tst.pruebatecnica.mapper.CrearUsuarioMapper;
import com.neoris.tst.pruebatecnica.repository.ClienteRepository;
import com.neoris.tst.pruebatecnica.request.CrearUsuarioRequest;
import com.neoris.tst.pruebatecnica.request.InactivarUsuarioRequest;
import com.neoris.tst.pruebatecnica.response.CrearUsuarioResponse;
import com.neoris.tst.pruebatecnica.response.InactivarUsuarioResponse;
import org.springframework.stereotype.Service;

import static com.neoris.tst.pruebatecnica.utility.MensajeExcepcionService.*;

@Service
public class ClienteServiceImpl implements ClienteService{

    private final ClienteRepository clienteRepository;
    private final PersonaService personaService;
    private final GeneroService generoService;



    public ClienteServiceImpl(
            ClienteRepository clienteRepository,
            PersonaService personaService,
            GeneroService generoService) {
        this.clienteRepository = clienteRepository;
        this.personaService = personaService;
        this.generoService = generoService;

    }

    @Override
    public CrearUsuarioResponse crearUsuario(CrearUsuarioRequest crearUsuarioRequest)
            throws GeneroException, PersonaException {

        Persona persona = CrearUsuarioMapper.requestToPersona(crearUsuarioRequest);
        Genero genero = generoService.buscarGeneroPorAbreviatura(crearUsuarioRequest.getGenero());
        Cliente cliente = CrearUsuarioMapper.requestToCliente(crearUsuarioRequest);

        persona.setGenero(genero);

        persona = personaService.guardarPersona(persona);
        cliente.setPersona(persona);

        cliente = clienteRepository.save(cliente);

        return CrearUsuarioMapper.domainToResponse(persona, cliente);
    }

    @Override
    public Cliente buscarClientePorNombreYEstado(String nombre, boolean estado)
            throws PersonaException, ClienteException {
        Persona persona = personaService.buscarPersonaPorNombreYEstado(nombre, estado);
        return clienteRepository
                .findByPersonaId(
                        persona.getId())
                .orElseThrow(
                        () ->new ClienteException(
                                String.format(CLIENTE_NO_EXISTE_POR_NOMBRE_MENSAJE,
                                        persona.getIdentificacion(), estado))
                );
    }

    @Override
    public InactivarUsuarioResponse inactivarUsuario(InactivarUsuarioRequest inactivarUsuarioRequest)
            throws PersonaException, ClienteException {
        // Consultar persona
        Persona persona = personaService.
                buscarPersonaPorIdentificacion(inactivarUsuarioRequest.getIdentificacion());

        Cliente cliente = clienteRepository.findByPersonaId(persona.getId()).orElseThrow(
                () -> new ClienteException(
                        String.format(CLIENTE_NO_EXISTE_POR_IDENTIFICACION_MENSAJE,
                                inactivarUsuarioRequest.getIdentificacion()))
        );

        if(!persona.getEstado() && !cliente.getEstado()) {
            throw new PersonaException(
                    String.format(PERSONA_YA_ESTA_INACTIVA_MENSAJE, inactivarUsuarioRequest.getIdentificacion()));
        }

        persona = personaService.inactivarPersona(persona);
        cliente.setEstado(false);
        cliente = clienteRepository.save(cliente);

        return InactivarUsuarioResponse.builder()
                .estado(persona.getEstado())
                .identificacion(persona.getIdentificacion())
                .nombre(persona.getNombre())
                .build();
    }
}
