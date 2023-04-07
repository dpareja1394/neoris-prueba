package com.neoris.tst.pruebatecnica.service;

import com.neoris.tst.pruebatecnica.domain.Cliente;
import com.neoris.tst.pruebatecnica.domain.Genero;
import com.neoris.tst.pruebatecnica.domain.Persona;
import com.neoris.tst.pruebatecnica.exception.ClienteNoExistePorIdentificacion;
import com.neoris.tst.pruebatecnica.exception.GeneroNoEncontradoPorAbreviatura;
import com.neoris.tst.pruebatecnica.exception.PersonaExistePorIdentificacion;
import com.neoris.tst.pruebatecnica.exception.PersonaNoExistePorNombre;
import com.neoris.tst.pruebatecnica.mapper.CrearUsuarioMapper;
import com.neoris.tst.pruebatecnica.repository.ClienteRepository;
import com.neoris.tst.pruebatecnica.request.CrearUsuarioRequest;
import com.neoris.tst.pruebatecnica.response.CrearUsuarioResponse;
import org.springframework.stereotype.Service;

import static com.neoris.tst.pruebatecnica.utility.MensajeExcepcionService.CLIENTE_NO_EXISTE_POR_IDENTIFICACION_MENSAJE;

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
            throws GeneroNoEncontradoPorAbreviatura, PersonaExistePorIdentificacion {

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
            throws PersonaNoExistePorNombre, ClienteNoExistePorIdentificacion {
        Persona persona = personaService.buscarPersonaPorNombreYEstado(nombre, estado);
        return clienteRepository
                .findByPersonaId(
                        persona.getId())
                .orElseThrow(
                        () ->new ClienteNoExistePorIdentificacion(
                                String.format(CLIENTE_NO_EXISTE_POR_IDENTIFICACION_MENSAJE,
                                        persona.getIdentificacion(), estado))
                );
    }
}
