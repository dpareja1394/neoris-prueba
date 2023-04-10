package com.neoris.tst.pruebatecnica.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.neoris.tst.pruebatecnica.utility.validations.CrearCuentaUsuarioValidate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CrearCuentaUsuarioRequest {
    @NotNull(message = CrearCuentaUsuarioValidate.NUMERO_CUENTA_REQUIRED)
    @NotEmpty(message = CrearCuentaUsuarioValidate.NUMERO_CUENTA_NOT_EMPTY)
    private String numeroCuenta;

    @JsonProperty("tipo")
    @NotNull(message = CrearCuentaUsuarioValidate.TIPO_CUENTA_DESCRIPCION_REQUIRED)
    @NotEmpty(message = CrearCuentaUsuarioValidate.TIPO_CUENTA_DESCRIPCION_NOT_EMPTY)
    private String tipoCuentaDescripcion;

    @NotNull(message = CrearCuentaUsuarioValidate.SALDO_INICIAL_NOT_NULL)
    @Min(value = CrearCuentaUsuarioValidate.SALDO_INICIAL_MIN_VALUE, message = CrearCuentaUsuarioValidate.SALDO_INICIAL_MIN)
    private BigDecimal saldoInicial;

    @JsonProperty("cliente")
    @NotNull(message = CrearCuentaUsuarioValidate.NOMBRE_NOT_NULL)
    @NotEmpty(message = CrearCuentaUsuarioValidate.NOMBRE_NOT_EMPTY)
    private String nombreCliente;

}
