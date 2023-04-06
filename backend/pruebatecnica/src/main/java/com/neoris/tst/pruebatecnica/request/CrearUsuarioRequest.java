package com.neoris.tst.pruebatecnica.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class CrearUsuarioRequest {

    @NotNull(message = "El campo nombres es obligatorio")
    private String nombres;

    @NotNull(message = "La identificación del usuario es obligatoria")
    private String identificacion;

    @NotNull(message = "La edad del usuario es obligatoria")
    @Min(value = 18, message = "La edad del usuario debe ser mayor o igual a 18 años")
    private Short edad;

    @NotNull(message = "La dirección del usuario es obligatoria")
    private String direccion;

    @NotNull(message = "El teléfono del usuario es obligatoria")
    private String telefono;

    @NotNull(message = "La contraseña es obligatoria")
    private String contrasena;

    @NotNull(message = "El género es requerido")
    private String genero;

    @NotNull(message = "Se debe ingresar el estado del usuario")
    private Boolean estado;

}