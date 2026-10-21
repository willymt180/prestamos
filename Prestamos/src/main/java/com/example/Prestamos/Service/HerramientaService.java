
package com.example.Prestamos.Service;

import com.example.Prestamos.Model.Herramienta;
import com.example.Prestamos.Repository.HerramientaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HerramientaService {

    private final HerramientaRepository herramientaRepository;

    public HerramientaService(
            HerramientaRepository herramientaRepository) {
        this.herramientaRepository = herramientaRepository;
    }

    @Transactional
    public Herramienta guardar(Herramienta herramienta) {

        if (herramienta.getCantidad() == null
                || herramienta.getCantidad() < 0) {
            throw new IllegalArgumentException(
                    "La cantidad no puede ser negativa ni nula");
        }

        if (herramienta.getNombre() == null
                || herramienta.getNombre().isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre es obligatorio");
        }

        if (herramienta.getCategoria() == null
                || herramienta.getCategoria().isBlank()) {
            throw new IllegalArgumentException(
                    "La categoria es obligatoria");
        }

        return herramientaRepository.save(herramienta);
    }

    @Transactional(readOnly = true)
    public List<Herramienta> listar() {
        return herramientaRepository.findAll();
    }
}
