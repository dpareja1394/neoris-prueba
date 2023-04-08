package com.neoris.tst.pruebatecnica.service;

import com.neoris.tst.pruebatecnica.domain.Genero;
import com.neoris.tst.pruebatecnica.exception.GeneroException;
import com.neoris.tst.pruebatecnica.repository.GeneroRepository;
import org.springframework.stereotype.Service;

import static com.neoris.tst.pruebatecnica.utility.MensajeExcepcionService.GENERO_NO_ENCONTRADO_MENSAJE;
import static com.neoris.tst.pruebatecnica.utility.MensajeExcepcionService.GENERO_NO_ENCONTRADO_POR_ID_MENSAJE;

@Service
public class GeneroServiceImpl implements GeneroService{

    private final GeneroRepository generoRepository;

    public GeneroServiceImpl(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    @Override
    public Genero buscarGeneroPorAbreviatura(String abreviatura) throws GeneroException {
        return generoRepository.findGeneroByAbreviaturaAndEstado(abreviatura, true).orElseThrow(
                () -> new GeneroException(String.format(GENERO_NO_ENCONTRADO_MENSAJE, abreviatura))
        );
    }
}
