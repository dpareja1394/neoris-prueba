package com.neoris.tst.pruebatecnica.controller;

import com.neoris.tst.pruebatecnica.service.MovimientoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class MovimientoControllerTest {

    @Autowired
    private MovimientoController movimientoController;

    @MockBean
    private MovimientoService movimientoService;

    @Test
    void realizarMovimiento() {

    }

}