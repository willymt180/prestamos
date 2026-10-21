
package com.example.Prestamos.Controller;

import com.example.Prestamos.Model.Herramienta;
import com.example.Prestamos.Service.HerramientaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/herramientas")
public class HerramientaController {

    private final HerramientaService herramientaService;

    public HerramientaController(
            HerramientaService herramientaService) {
        this.herramientaService = herramientaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Herramienta crear(
            @Valid @RequestBody Herramienta herramienta) {
        return herramientaService.guardar(herramienta);
    }

    @GetMapping
    public List<Herramienta> listar() {
        return herramientaService.listar();
    }
}
