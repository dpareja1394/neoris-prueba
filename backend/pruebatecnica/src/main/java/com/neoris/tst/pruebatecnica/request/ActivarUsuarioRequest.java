package com.neoris.tst.pruebatecnica.request;

import com.neoris.tst.pruebatecnica.utility.validations.ActivarUsuarioValidate;
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
public class ActivarUsuarioRequest {
    @NotNull(message = ActivarUsuarioValidate.IDENTIFICACION_NOT_NULL)
    @NotEmpty(message = ActivarUsuarioValidate.IDENTIFICACION_NOT_EMPTY)
    private String identificacion;
}
