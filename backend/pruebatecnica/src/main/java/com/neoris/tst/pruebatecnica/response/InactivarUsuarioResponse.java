package com.neoris.tst.pruebatecnica.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class InactivarUsuarioResponse {
    private String identificacion;
    private String nombre;
    private Boolean estado;
}
