
package com.example.Prestamos.Service;

import com.example.Prestamos.dto.PrestamoRequest;
import com.example.Prestamos.Model.Herramienta;
import com.example.Prestamos.Model.Prestamo;
import com.example.Prestamos.Repository.HerramientaRepository;
import com.example.Prestamos.Repository.PrestamoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final HerramientaRepository herramientaRepository;

    public PrestamoService(
            PrestamoRepository prestamoRepository,
            HerramientaRepository herramientaRepository) {
        this.prestamoRepository = prestamoRepository;
        this.herramientaRepository = herramientaRepository;
    }

    @Transactional
    public Prestamo registrar(PrestamoRequest request) {

        if (request.getCantidad() == null
                || request.getCantidad() <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad debe ser mayor que cero");
        }

        if (request.getResponsable() == null
                || request.getResponsable().isBlank()) {
            throw new IllegalArgumentException(
                    "El responsable es obligatorio");
        }

        if (request.getFechaPrestamo() == null) {
            throw new IllegalArgumentException(
                    "La fecha del prestamo es obligatoria");
        }

        if (request.getHerramientaId() == null
                || request.getHerramientaId() <= 0) {
            throw new IllegalArgumentException(
                    "El ID de la herramienta no es valido");
        }

        Herramienta herramienta = herramientaRepository
                .buscarPorIdConBloqueo(request.getHerramientaId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "La herramienta no existe"));

        if (herramienta.getCantidad() < request.getCantidad()) {
            throw new IllegalArgumentException(
                    "No hay suficientes unidades disponibles. "
                            + "Disponibles: " + herramienta.getCantidad());
        }

        herramienta.setCantidad(
                herramienta.getCantidad() - request.getCantidad());

        herramientaRepository.save(herramienta);

        Prestamo prestamo = new Prestamo(
                request.getResponsable().trim(),
                request.getFechaPrestamo(),
                request.getCantidad(),
                herramienta
        );

        return prestamoRepository.save(prestamo);
    }

    @Transactional(readOnly = true)
    public List<Prestamo> listar() {
        return prestamoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Prestamo> listarPorHerramienta(Long herramientaId) {

        if (!herramientaRepository.existsById(herramientaId)) {
            throw new IllegalArgumentException(
                    "La herramienta no existe");
        }

        return prestamoRepository.findByHerramienta_Id(herramientaId);
    }
}
