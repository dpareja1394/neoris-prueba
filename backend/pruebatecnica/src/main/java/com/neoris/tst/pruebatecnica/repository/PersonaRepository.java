package com.neoris.tst.pruebatecnica.repository;

import com.neoris.tst.pruebatecnica.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    boolean existsByIdentificacionAndEstado(String identificacion, Boolean estado);
    Optional<Persona> findByNombreAndEstado(String nombre, Boolean estado);
}
