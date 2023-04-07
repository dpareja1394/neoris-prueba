package com.neoris.tst.pruebatecnica.response;

import com.neoris.tst.pruebatecnica.utility.validations.InactivarUsuarioValidate;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class InactivarUsuarioResponse {
    private String identificacion;
    private String nombre;
    private Boolean estado;
}
