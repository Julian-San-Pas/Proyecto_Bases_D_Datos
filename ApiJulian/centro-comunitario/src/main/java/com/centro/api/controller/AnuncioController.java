package com.centro.api.controller;

import com.centro.api.model.Anuncio;
import com.centro.api.repository.AnuncioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/anuncios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AnuncioController {

    private final AnuncioRepository anuncioRepository;

    @PostMapping
    public ResponseEntity<Anuncio> crear(@Valid @RequestBody Anuncio anuncio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(anuncioRepository.save(anuncio));
    }

    @GetMapping
    public ResponseEntity<List<Anuncio>> listar() {
        return ResponseEntity.ok(anuncioRepository.findByPublicado(true));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Anuncio>> listarTodos() {
        return ResponseEntity.ok(anuncioRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anuncio> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(anuncioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anuncio no encontrado")));
    }

    @GetMapping("/actividad/{actividadId}")
    public ResponseEntity<List<Anuncio>> porActividad(@PathVariable String actividadId) {
        return ResponseEntity.ok(anuncioRepository.findByActividadId(actividadId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Anuncio> actualizar(@PathVariable String id, @Valid @RequestBody Anuncio anuncio) {
        anuncioRepository.findById(id).orElseThrow(() -> new RuntimeException("Anuncio no encontrado"));
        anuncio.setId(id);
        return ResponseEntity.ok(anuncioRepository.save(anuncio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        anuncioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
