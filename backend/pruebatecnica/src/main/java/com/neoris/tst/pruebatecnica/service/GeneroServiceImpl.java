package com.neoris.tst.pruebatecnica.service;

import com.neoris.tst.pruebatecnica.domain.Genero;
import com.neoris.tst.pruebatecnica.exception.GeneroNoEncontradoPorAbreviatura;
import com.neoris.tst.pruebatecnica.repository.GeneroRepository;
import org.springframework.stereotype.Service;

@Service
public class GeneroServiceImpl implements GeneroService{

    private final GeneroRepository generoRepository;
    public final static String GENERO_NO_ENCONTRADO_MENSAJE = "El género %s no se ha encontrado";

    public GeneroServiceImpl(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    @Override
    public Genero buscarGeneroPorAbreviatura(String abreviatura) throws GeneroNoEncontradoPorAbreviatura {
        Genero genero = generoRepository.findGeneroByAbreviatura(abreviatura);
        if(genero == null) {
            throw new GeneroNoEncontradoPorAbreviatura(String.format(GENERO_NO_ENCONTRADO_MENSAJE, abreviatura));
        }
        return genero;
    }
}
