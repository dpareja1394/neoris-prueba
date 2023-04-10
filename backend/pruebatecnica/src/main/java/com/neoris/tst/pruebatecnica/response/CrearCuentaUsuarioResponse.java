package com.neoris.tst.pruebatecnica.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class CrearCuentaUsuarioResponse {

    private String numeroCuenta;

    @JsonProperty("tipo")
    private String tipoCuentaDescripcion;

    private BigDecimal saldoInicial;

    private Boolean estado;

    @JsonProperty("cliente")
    private String nombreCliente;

}
