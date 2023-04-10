package com.neoris.tst.pruebatecnica.mapper;

import com.neoris.tst.pruebatecnica.domain.Cuenta;
import com.neoris.tst.pruebatecnica.domain.TipoCuenta;
import com.neoris.tst.pruebatecnica.request.CrearCuentaUsuarioRequest;
import com.neoris.tst.pruebatecnica.response.CrearCuentaUsuarioResponse;

public class CrearCuentaUsuarioMapper {

    public static Cuenta requestToCuenta(CrearCuentaUsuarioRequest cuentaUsuarioRequest) {
        return Cuenta.builder()
                .numeroCuenta(cuentaUsuarioRequest.getNumeroCuenta())
                .estado(true)
                .saldoInicial(cuentaUsuarioRequest.getSaldoInicial())
                .saldoActual(cuentaUsuarioRequest.getSaldoInicial())
                .build();
    }

    public static CrearCuentaUsuarioResponse domainToResponse
            (Cuenta cuenta, TipoCuenta tipoCuenta, String nombreCliente) {
        return CrearCuentaUsuarioResponse.builder()
                .numeroCuenta(cuenta.getNumeroCuenta())
                .tipoCuentaDescripcion(tipoCuenta.getDescripcion())
                .saldoInicial(cuenta.getSaldoInicial())
                .estado(cuenta.getEstado())
                .nombreCliente(nombreCliente)
                .build();
    }

}
