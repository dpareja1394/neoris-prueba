package com.neoris.tst.pruebatecnica.request;

import com.neoris.tst.pruebatecnica.utility.validations.InactivarUsuarioValidate;
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
public class InactivarUsuarioRequest {
    @NotNull(message = InactivarUsuarioValidate.IDENTIFICACION_NOT_NULL)
    @NotEmpty(message = InactivarUsuarioValidate.IDENTIFICACION_NOT_EMPTY)
    private String identificacion;
}
