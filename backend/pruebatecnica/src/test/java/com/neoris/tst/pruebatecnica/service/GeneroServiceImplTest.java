package com.neoris.tst.pruebatecnica.service;

import com.neoris.tst.pruebatecnica.domain.Genero;
import com.neoris.tst.pruebatecnica.exception.GeneroNoEncontradoPorAbreviatura;
import com.neoris.tst.pruebatecnica.repository.GeneroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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

        when(generoRepository.findGeneroByAbreviatura("M")).thenReturn(genero);
        Genero result = null;
        try {
            result = generoService.buscarGeneroPorAbreviatura("M");
        } catch (GeneroNoEncontradoPorAbreviatura e) {
            Assertions.fail("No debería fallar la prueba");
        }
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Masculino", result.getDescripcion());
        Assertions.assertEquals(1, result.getId());
    }

    @Test
    public void testBuscarGeneroPorAbreviaturaFail() {
        assertThrows(GeneroNoEncontradoPorAbreviatura.class, () -> {
            generoService.buscarGeneroPorAbreviatura("M");
        });
    }

}
