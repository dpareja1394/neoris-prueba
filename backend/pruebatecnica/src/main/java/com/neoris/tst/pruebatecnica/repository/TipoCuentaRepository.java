package com.neoris.tst.pruebatecnica.repository;

import com.neoris.tst.pruebatecnica.domain.TipoCuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoCuentaRepository extends JpaRepository<TipoCuenta, Integer> {
    Optional<TipoCuenta> findByDescripcionAndEstado(String descripcion, Boolean estado);
}
