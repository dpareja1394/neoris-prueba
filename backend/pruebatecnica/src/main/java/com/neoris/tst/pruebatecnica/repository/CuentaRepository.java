package com.neoris.tst.pruebatecnica.repository;

import com.neoris.tst.pruebatecnica.domain.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {
    boolean existsByNumeroCuentaAndClienteIdAndTipoCuentaIdAndEstado
            (String numeroCuenta, Integer clienteId, Integer tipoCuentaId, Boolean estado);

    Optional<Cuenta> findCuentaByNumeroCuentaAndTipoCuentaIdAndEstado
            (String numeroCuenta, Integer tipoCuentaId, Boolean estado);

    boolean existsByClienteId (Integer clienteId);
}
