package com.neoris.tst.pruebatecnica.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
@ToString
public class BuscarCuentaResponse {
    private String numeroCuenta;
    private String cliente;
    private String tipoCuenta;
    private BigDecimal saldoActual;
    private Boolean estado;
}
