package com.neoris.tst.pruebatecnica.service;

import com.neoris.tst.pruebatecnica.domain.Genero;
import com.neoris.tst.pruebatecnica.exception.GeneroNoEncontradoPorAbreviatura;

public interface GeneroService {

    Genero buscarGeneroPorAbreviatura(String abreviatura) throws GeneroNoEncontradoPorAbreviatura;

}
