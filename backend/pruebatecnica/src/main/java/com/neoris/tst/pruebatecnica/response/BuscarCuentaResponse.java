package com.neoris.tst.pruebatecnica.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class BuscarCuentaResponse {
    private String numeroCuenta;
    private String cliente;
    private String tipoCuenta;
    private BigDecimal saldoActual;
    private Boolean estado;
}
