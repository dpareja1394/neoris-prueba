package com.neoris.tst.pruebatecnica.service;

import com.neoris.tst.pruebatecnica.domain.Cliente;
import com.neoris.tst.pruebatecnica.domain.Cuenta;
import com.neoris.tst.pruebatecnica.domain.TipoCuenta;
import com.neoris.tst.pruebatecnica.exception.*;
import com.neoris.tst.pruebatecnica.mapper.CrearCuentaUsuarioMapper;
import com.neoris.tst.pruebatecnica.repository.CuentaRepository;
import com.neoris.tst.pruebatecnica.request.CrearCuentaUsuarioRequest;
import com.neoris.tst.pruebatecnica.response.CrearCuentaUsuarioResponse;
import org.springframework.stereotype.Service;

import static com.neoris.tst.pruebatecnica.utility.MensajeExcepcionService.CUENTA_EXISTE_POR_CLIENTE_TIPO_MENSAJE;
import static com.neoris.tst.pruebatecnica.utility.MensajeExcepcionService.CUENTA_NO_EXISTE_POR_NUMERO_TIPO_MENSAJE;

@Service
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;
    private final TipoCuentaService tipoCuentaService;
    private final ClienteService clienteService;

    public CuentaServiceImpl(CuentaRepository cuentaRepository, ClienteService clienteService, TipoCuentaService tipoCuentaService) {
        this.cuentaRepository = cuentaRepository;
        this.tipoCuentaService = tipoCuentaService;
        this.clienteService = clienteService;
    }

    @Override
    public CrearCuentaUsuarioResponse crearCuentaUsuario(CrearCuentaUsuarioRequest crearCuentaUsuarioRequest)
            throws PersonaException,
            TipoCuentaException, CuentaException, ClienteException {
        Cliente cliente = clienteService.buscarClientePorNombreYEstado
                (crearCuentaUsuarioRequest.getNombreCliente(), true);

        TipoCuenta tipoCuenta = tipoCuentaService.buscarTipoCuentaPorDescripcionYEstado
                (crearCuentaUsuarioRequest.getTipoCuentaDescripcion(), true);

        if (cuentaRepository.existsByNumeroCuentaAndClienteIdAndTipoCuentaIdAndEstado
                (crearCuentaUsuarioRequest.getNumeroCuenta(), cliente.getId(), tipoCuenta.getId(), true)) {
            throw new CuentaException(
                    String.format(CUENTA_EXISTE_POR_CLIENTE_TIPO_MENSAJE,
                            tipoCuenta.getDescripcion().toLowerCase(),
                            crearCuentaUsuarioRequest.getNumeroCuenta(),
                            crearCuentaUsuarioRequest.getNombreCliente(),
                            true));
        }

        Cuenta cuenta = CrearCuentaUsuarioMapper.requestToCuenta(crearCuentaUsuarioRequest);
        cuenta.setTipoCuenta(tipoCuenta);
        cuenta.setCliente(cliente);

        cuenta = cuentaRepository.save(cuenta);

        return CrearCuentaUsuarioMapper.domainToResponse(cuenta, tipoCuenta, crearCuentaUsuarioRequest.getNombreCliente());
    }

    @Override
    public Cuenta buscarCuentaPorNumeroYTipoCuenta(String numeroCuenta, String tipoCuentaDescripcion)
            throws TipoCuentaException, CuentaException {

        TipoCuenta tipoCuenta = tipoCuentaService.buscarTipoCuentaPorDescripcionYEstado
                (tipoCuentaDescripcion, true);

        return cuentaRepository
                .findCuentaByNumeroCuentaAndTipoCuentaIdAndEstado(numeroCuenta, tipoCuenta.getId(), true)
                .orElseThrow(
                        () -> new CuentaException(
                                String.format(CUENTA_NO_EXISTE_POR_NUMERO_TIPO_MENSAJE, tipoCuentaDescripcion, numeroCuenta, true)
                        )
                );
    }

    @Override
    public Cuenta efectuarMovimientoEnCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    @Override
    public Boolean existenCuentasPorCliente(Integer clienteId) {
        return cuentaRepository.existsByClienteId(clienteId);
    }
}
