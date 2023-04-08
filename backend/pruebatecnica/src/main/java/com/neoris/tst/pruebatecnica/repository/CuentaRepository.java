package com.neoris.tst.pruebatecnica.repository;

import com.neoris.tst.pruebatecnica.domain.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {
    boolean existsByNumeroCuentaAndClienteIdAndTipoCuentaId
            (String numeroCuenta, Integer clienteId, Integer tipoCuentaId);

    Optional<Cuenta> findCuentaByNumeroCuentaAndTipoCuentaIdAndEstado
            (String numeroCuenta, Integer tipoCuentaId, Boolean estado);

    Optional<Cuenta> findByNumeroCuentaAndClienteIdAndTipoCuentaId
            (String numeroCuenta, Integer clienteId, Integer tipoCuentaId);
}
