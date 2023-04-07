package com.neoris.tst.pruebatecnica.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class RealizarMovimientoResponse {
    private String numeroCuenta;

    @JsonProperty("tipo")
    private String tipoCuentaDescripcion;

    private BigDecimal saldoInicial;

    private boolean estado;

    @JsonProperty("movimiento")
    private String tipoMovimientoDescripcion;

    private BigDecimal valor;
}
