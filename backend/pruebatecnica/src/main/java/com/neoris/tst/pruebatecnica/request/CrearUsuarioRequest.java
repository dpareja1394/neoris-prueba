package com.neoris.tst.pruebatecnica.request;

import com.neoris.tst.pruebatecnica.utility.validations.CrearUsuarioValidate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrearUsuarioRequest {

    @NotNull(message = CrearUsuarioValidate.NOMBRE_REQUIRED)
    @NotEmpty(message = CrearUsuarioValidate.NOMBRE_NOT_EMPTY)
    private String nombres;

    @NotNull(message = CrearUsuarioValidate.IDENTIFICACION_NOT_NULL)
    @NotEmpty(message = CrearUsuarioValidate.IDENTIFICACION_NOT_EMPTY)
    private String identificacion;

    @NotNull(message = CrearUsuarioValidate.EDAD_NOT_NULL)
    @Min(value = 18, message = CrearUsuarioValidate.EDAD_MIN)
    private Short edad;

    @NotNull(message = CrearUsuarioValidate.DIRECCION_NOT_NULL)
    @NotEmpty(message = CrearUsuarioValidate.DIRECCION_NOT_EMPTY)
    private String direccion;

    @NotNull(message = CrearUsuarioValidate.TELEFONO_NOT_NULL)
    @NotEmpty(message = CrearUsuarioValidate.TELEFONO_NOT_EMPTY)
    private String telefono;

    @NotNull(message = CrearUsuarioValidate.CONTRASENA_NOT_NULL)
    @NotEmpty(message = CrearUsuarioValidate.CONTRASENA_NOT_EMPTY)
    private String contrasena;

    @NotNull(message = CrearUsuarioValidate.GENERO_NOT_NULL)
    @NotEmpty(message = CrearUsuarioValidate.GENERO_NOT_EMPTY)
    private String genero;

    @NotNull(message = CrearUsuarioValidate.ESTADO_NOT_NULL)
    private Boolean estado;

}