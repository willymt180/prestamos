
package com.example.Prestamos.Repository;

import com.example.Prestamos.Model.Prestamo;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PrestamoRepository
        extends JpaRepository<Prestamo, Long> {

    List<Prestamo> findByHerramienta_Id(Long herramientaId);
}
