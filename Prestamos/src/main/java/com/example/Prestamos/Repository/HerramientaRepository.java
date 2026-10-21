
package com.example.Prestamos.Repository;

import com.example.Prestamos.Model.Herramienta;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HerramientaRepository
        extends JpaRepository<Herramienta, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT h FROM Herramienta h WHERE h.id = :id")
    Optional<Herramienta> buscarPorIdConBloqueo(@Param("id") Long id);
}
