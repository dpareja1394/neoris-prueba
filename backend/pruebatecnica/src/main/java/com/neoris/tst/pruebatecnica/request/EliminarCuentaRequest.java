package com.neoris.tst.pruebatecnica.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.neoris.tst.pruebatecnica.utility.validations.ActivarInactivarCuentaValidate;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EliminarCuentaRequest {

    @NotNull(message = ActivarInactivarCuentaValidate.NUMERO_CUENTA_REQUIRED)
    @NotEmpty(message = ActivarInactivarCuentaValidate.NUMERO_CUENTA_NOT_EMPTY)
    private String numeroCuenta;

    @JsonProperty("tipo")
    @NotNull(message = ActivarInactivarCuentaValidate.TIPO_CUENTA_DESCRIPCION_REQUIRED)
    @NotEmpty(message = ActivarInactivarCuentaValidate.TIPO_CUENTA_DESCRIPCION_NOT_EMPTY)
    private String tipoCuentaDescripcion;

    boolean force;
}