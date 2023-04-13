package com.neoris.tst.pruebatecnica.service.implementation;

import com.neoris.tst.pruebatecnica.domain.Cliente;
import com.neoris.tst.pruebatecnica.domain.Cuenta;
import com.neoris.tst.pruebatecnica.domain.TipoCuenta;
import com.neoris.tst.pruebatecnica.exception.ClienteException;
import com.neoris.tst.pruebatecnica.exception.CuentaException;
import com.neoris.tst.pruebatecnica.exception.PersonaException;
import com.neoris.tst.pruebatecnica.exception.TipoCuentaException;
import com.neoris.tst.pruebatecnica.mapper.BuscarCuentaMapper;
import com.neoris.tst.pruebatecnica.mapper.CrearCuentaUsuarioMapper;
import com.neoris.tst.pruebatecnica.repository.CuentaRepository;
import com.neoris.tst.pruebatecnica.repository.MovimientoRepository;
import com.neoris.tst.pruebatecnica.request.ActivarCuentaRequest;
import com.neoris.tst.pruebatecnica.request.CrearCuentaUsuarioRequest;
import com.neoris.tst.pruebatecnica.request.InactivarCuentaRequest;
import com.neoris.tst.pruebatecnica.response.ActivarCuentaResponse;
import com.neoris.tst.pruebatecnica.response.BuscarCuentaResponse;
import com.neoris.tst.pruebatecnica.response.CrearCuentaUsuarioResponse;
import com.neoris.tst.pruebatecnica.response.InactivarCuentaResponse;
import com.neoris.tst.pruebatecnica.service.ClienteService;
import com.neoris.tst.pruebatecnica.service.CuentaService;
import com.neoris.tst.pruebatecnica.service.TipoCuentaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.neoris.tst.pruebatecnica.utility.MensajeExcepcionService.*;

@Service
@Slf4j
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;
    private final TipoCuentaService tipoCuentaService;
    private final ClienteService clienteService;

    private final MovimientoRepository movimientoRepository;

    public CuentaServiceImpl(CuentaRepository cuentaRepository, ClienteService clienteService, TipoCuentaService tipoCuentaService, MovimientoRepository movimientoRepository) {
        this.cuentaRepository = cuentaRepository;
        this.tipoCuentaService = tipoCuentaService;
        this.clienteService = clienteService;
        this.movimientoRepository = movimientoRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CrearCuentaUsuarioResponse crearCuentaUsuario(CrearCuentaUsuarioRequest crearCuentaUsuarioRequest)
            throws PersonaException, TipoCuentaException, CuentaException, ClienteException {
        Cliente cliente = clienteService.
                buscarClientePorNombreYEstado(crearCuentaUsuarioRequest.getNombreCliente(), true);

        TipoCuenta tipoCuenta = tipoCuentaService.
                buscarTipoCuentaPorDescripcionYEstado(crearCuentaUsuarioRequest.getTipoCuentaDescripcion(), true);

        if (cuentaRepository.
                existsByNumeroCuentaAndClienteIdAndTipoCuentaId(
                        crearCuentaUsuarioRequest.getNumeroCuenta(), cliente.getId(), tipoCuenta.getId())) {
            throw new CuentaException(
                    String.format(CUENTA_EXISTE_POR_CLIENTE_TIPO_MENSAJE,
                            tipoCuenta.getDescripcion().toLowerCase(), crearCuentaUsuarioRequest.getNumeroCuenta(),
                            crearCuentaUsuarioRequest.getNombreCliente(), true));
        }

        Cuenta cuenta = CrearCuentaUsuarioMapper.requestToCuenta(crearCuentaUsuarioRequest);
        cuenta.setTipoCuenta(tipoCuenta);
        cuenta.setCliente(cliente);

        cuenta = cuentaRepository.save(cuenta);

        return CrearCuentaUsuarioMapper.domainToResponse(cuenta, tipoCuenta, crearCuentaUsuarioRequest.getNombreCliente());
    }

    @Override
    @Transactional(readOnly = true)
    public Cuenta buscarCuentaPorNumeroYTipoCuenta(String numeroCuenta, String tipoCuentaDescripcion)
            throws TipoCuentaException, CuentaException {
        return cuentaRepository.
                findCuentaByNumeroCuentaAndTipoCuentaId(numeroCuenta,
                        tipoCuentaService.buscarTipoCuentaPorDescripcionYEstado(tipoCuentaDescripcion, true).getId()).
                orElseThrow(() -> new CuentaException(String.format(CUENTA_NO_EXISTE_POR_NUMERO_TIPO_MENSAJE,
                        tipoCuentaDescripcion, numeroCuenta)));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Cuenta efectuarMovimientoEnCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    @Override
    @Transactional(readOnly = true)
    public BuscarCuentaResponse consultarCuentaPorNumeroYTipoCuenta(String numeroCuenta, String tipoCuentaDescripcion)
            throws CuentaException, TipoCuentaException {
        return BuscarCuentaMapper.domainToResponse(buscarCuentaPorNumeroYTipoCuenta(numeroCuenta, tipoCuentaDescripcion));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BuscarCuentaResponse> consultarCuentasPorUsuario(String identificacion) throws PersonaException, ClienteException {
        return BuscarCuentaMapper.domainToResponseList(clienteService.buscarClientePorIdentificacion(identificacion).getCuentas());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public InactivarCuentaResponse inactivarCuenta(InactivarCuentaRequest inactivarCuentaRequest) throws CuentaException, TipoCuentaException, PersonaException, ClienteException {
        Cuenta cuenta = buscarCuentaPorNumeroCuentaTipoCuentaIdentificacionCliente(inactivarCuentaRequest.getNumeroCuenta(),
                inactivarCuentaRequest.getTipoCuentaDescripcion(), inactivarCuentaRequest.getIdentificacion());
        if (!cuenta.getEstado()) {
            throw new CuentaException(String.format(CUENTA_YA_ESTA_INACTIVA, cuenta.getTipoCuenta().getDescripcion(), cuenta.getNumeroCuenta()));
        }

        cuenta.setEstado(false);
        cuenta = cuentaRepository.save(cuenta);

        return InactivarCuentaResponse.builder()
                .tipoCuentaDescripcion(cuenta.getTipoCuenta().getDescripcion())
                .numeroCuenta(cuenta.getNumeroCuenta())
                .estado(cuenta.getEstado())
                .identificacion(cuenta.getCliente().getPersona().getIdentificacion())
                .build();
    }

    @Transactional(readOnly = true)
    public Cuenta buscarCuentaPorNumeroCuentaTipoCuentaIdentificacionCliente(String numeroCuenta, String tipoCuentaDescripcion, String identificacionCliente)
            throws PersonaException, ClienteException, TipoCuentaException, CuentaException {
        Cliente cliente = clienteService.buscarClientePorIdentificacion(identificacionCliente);
        TipoCuenta tipoCuenta = tipoCuentaService.buscarTipoCuentaPorDescripcionYEstado(tipoCuentaDescripcion, true);
        return cuentaRepository.
                findByNumeroCuentaAndClienteIdAndTipoCuentaId(numeroCuenta, cliente.getId(), tipoCuenta.getId()).
                orElseThrow(
                        () -> new CuentaException(String.format(CUENTA_NO_EXISTE_POR_CLIENTE_TIPO_MENSAJE,
                                tipoCuenta.getDescripcion().toLowerCase(), numeroCuenta, cliente.getPersona().getNombre(), true)));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ActivarCuentaResponse activarCuenta(ActivarCuentaRequest activarCuentaRequest) throws CuentaException, TipoCuentaException, PersonaException, ClienteException {
        Cuenta cuenta = buscarCuentaPorNumeroCuentaTipoCuentaIdentificacionCliente(activarCuentaRequest.getNumeroCuenta(),
                activarCuentaRequest.getTipoCuentaDescripcion(), activarCuentaRequest.getIdentificacion());
        if (cuenta.getEstado()) {
            throw new CuentaException(String.format(CUENTA_YA_ESTA_ACTIVA,
                    cuenta.getTipoCuenta().getDescripcion(), cuenta.getNumeroCuenta()));
        }

        cuenta.setEstado(true);
        cuenta = cuentaRepository.save(cuenta);

        return ActivarCuentaResponse.builder()
                .tipoCuentaDescripcion(cuenta.getTipoCuenta().getDescripcion())
                .numeroCuenta(cuenta.getNumeroCuenta())
                .estado(cuenta.getEstado())
                .identificacion(cuenta.getCliente().getPersona().getIdentificacion())
                .build();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String eliminarCuenta(String numeroCuenta, String tipoCuenta, boolean force) throws CuentaException, TipoCuentaException {
        Cuenta cuenta = buscarCuentaPorNumeroYTipoCuenta(numeroCuenta, tipoCuenta);
        if (force) {
            movimientoRepository.deleteAll(cuenta.getMovimientos());
        } else {
            if (!cuenta.getMovimientos().isEmpty()) {
                throw new CuentaException(CUENTA_TIENE_MOVIMIENTOS_MENSAJE);
            }
        }
        String mensajeExito = String.format(CUENTA_ELIMINADA, cuenta.getTipoCuenta().getDescripcion(), cuenta.getNumeroCuenta());

        cuentaRepository.delete(cuenta);

        return mensajeExito;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cuenta> consultarListadoCuentasPorUsuario(String identificacion) throws PersonaException, ClienteException {
        return clienteService.buscarClientePorIdentificacion(identificacion).getCuentas();
    }

    @Override
    @Transactional(readOnly = true)
    public Cuenta consultarYValidarCuenta(String numeroCuenta, String tipoCuentaDescripcion) throws CuentaException, TipoCuentaException {
        Cuenta cuenta = buscarCuentaPorNumeroYTipoCuenta(numeroCuenta, tipoCuentaDescripcion);

        if (!cuenta.getEstado()) {
            throw new CuentaException(MOVIMIENTO_ERROR_CUENTA_INACTIVA);
        }
        return cuenta;
    }
}
