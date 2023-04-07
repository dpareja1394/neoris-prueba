package com.neoris.tst.pruebatecnica.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.neoris.tst.pruebatecnica.utility.validations.RealizarMovimientoValidate;
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
public class RealizarMovimientoRequest {
    @NotNull(message = RealizarMovimientoValidate.NUMERO_CUENTA_REQUIRED)
    @NotEmpty(message = RealizarMovimientoValidate.NUMERO_CUENTA_NOT_EMPTY)
    private String numeroCuenta;

    @JsonProperty("tipo")
    @NotNull(message = RealizarMovimientoValidate.TIPO_CUENTA_DESCRIPCION_REQUIRED)
    @NotEmpty(message = RealizarMovimientoValidate.TIPO_CUENTA_DESCRIPCION_NOT_EMPTY)
    private String tipoCuentaDescripcion;

    @JsonProperty("movimiento")
    @NotNull(message = RealizarMovimientoValidate.TIPO_MOVIMIENTO_NOT_NULL)
    @NotEmpty(message = RealizarMovimientoValidate.TIPO_MOVIMIENTO_NOT_EMPTY)
    private String tipoMovimientoDescripcion;

    @NotNull(message = RealizarMovimientoValidate.VALOR_NOT_NULL)
    @Min(value = RealizarMovimientoValidate.VALOR_MIN_VALUE, message = RealizarMovimientoValidate.VALOR_MIN)
    private BigDecimal valor;

}
