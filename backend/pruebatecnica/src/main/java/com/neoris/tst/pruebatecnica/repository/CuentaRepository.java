package com.neoris.tst.pruebatecnica.repository;

import com.neoris.tst.pruebatecnica.domain.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {
    boolean existsByNumeroCuentaAndClienteIdAndTipoCuentaIdAndEstado
            (String numeroCuenta, Integer clienteId, Integer tipoCuentaId, Boolean estado);
}
