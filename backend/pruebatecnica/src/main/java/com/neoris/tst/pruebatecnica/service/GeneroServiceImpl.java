package com.neoris.tst.pruebatecnica.service;

import com.neoris.tst.pruebatecnica.domain.Genero;
import com.neoris.tst.pruebatecnica.exception.GeneroNoEncontradoPorAbreviatura;
import com.neoris.tst.pruebatecnica.repository.GeneroRepository;
import org.springframework.stereotype.Service;

import static com.neoris.tst.pruebatecnica.utility.MensajeExcepcionService.GENERO_NO_ENCONTRADO_MENSAJE;

@Service
public class GeneroServiceImpl implements GeneroService{

    private final GeneroRepository generoRepository;

    public GeneroServiceImpl(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    @Override
    public Genero buscarGeneroPorAbreviatura(String abreviatura) throws GeneroNoEncontradoPorAbreviatura {
        Genero genero = generoRepository.findGeneroByAbreviaturaAndEstado(abreviatura, true);
        if(genero == null) {
            throw new GeneroNoEncontradoPorAbreviatura(String.format(GENERO_NO_ENCONTRADO_MENSAJE, abreviatura));
        }
        return genero;
    }
}
