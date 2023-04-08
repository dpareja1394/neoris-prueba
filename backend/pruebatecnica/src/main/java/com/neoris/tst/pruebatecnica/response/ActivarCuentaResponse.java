package com.neoris.tst.pruebatecnica.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ActivarCuentaResponse {

    private String numeroCuenta;

    @JsonProperty("tipo")
    private String tipoCuentaDescripcion;

    private Boolean estado;

    @JsonProperty("cliente")
    private String identificacion;

}
