package com.neoris.tst.pruebatecnica.service;

import com.neoris.tst.pruebatecnica.domain.Genero;
import com.neoris.tst.pruebatecnica.exception.GeneroException;
import com.neoris.tst.pruebatecnica.repository.GeneroRepository;
import com.neoris.tst.pruebatecnica.service.implementation.GeneroServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GeneroServiceImplTest {

    private GeneroService generoService;
    private GeneroRepository generoRepository;

    @BeforeEach
    public void setUp() {
        generoRepository = mock(GeneroRepository.class);
        generoService = new GeneroServiceImpl(generoRepository);
    }

    @Test
    public void testBuscarGeneroPorAbreviatura() {
        Genero genero = Genero.builder()
                .id(1)
                .abreviatura("M")
                .descripcion("Masculino")
                .estado(true)
                .build();

        when(generoRepository.findGeneroByAbreviaturaAndEstado("M", true)).thenReturn(Optional.of(genero));
        Genero result = null;
        try {
            result = generoService.buscarGeneroPorAbreviatura("M");
        } catch (GeneroException e) {
            Assertions.fail("No debería fallar la prueba");
        }
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Masculino", result.getDescripcion());
        Assertions.assertEquals(1, result.getId());
    }

    @Test
    public void testBuscarGeneroPorAbreviaturaFail() {
        assertThrows(GeneroException.class, () -> {
            generoService.buscarGeneroPorAbreviatura("M");
        });
    }

}
