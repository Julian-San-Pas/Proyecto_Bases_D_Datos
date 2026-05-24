package com.centro.api.controller;

import com.centro.api.model.Foro;
import com.centro.api.repository.ForoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/foros")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ForoController {

    private final ForoRepository foroRepository;

    @PostMapping
    public ResponseEntity<Foro> crear(@Valid @RequestBody Foro foro) {
        foro.setComentarios(new ArrayList<>());
        return ResponseEntity.status(HttpStatus.CREATED).body(foroRepository.save(foro));
    }

    @GetMapping
    public ResponseEntity<List<Foro>> listar() {
        return ResponseEntity.ok(foroRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Foro> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(foroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Foro no encontrado")));
    }

    @GetMapping("/actividad/{actividadId}")
    public ResponseEntity<List<Foro>> porActividad(@PathVariable String actividadId) {
        return ResponseEntity.ok(foroRepository.findByActividadId(actividadId));
    }

    @PostMapping("/{id}/comentarios")
    public ResponseEntity<Foro> agregarComentario(@PathVariable String id,
                                                   @RequestBody Map<String, String> body) {
        Foro foro = foroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Foro no encontrado"));
        if (!foro.isAbierto()) {
            throw new RuntimeException("El foro está cerrado");
        }
        Foro.Comentario comentario = Foro.Comentario.builder()
                .id(UUID.randomUUID().toString())
                .autorId(body.get("autorId"))
                .contenido(body.get("contenido"))
                .fechaComentario(LocalDateTime.now())
                .build();
        if (foro.getComentarios() == null) foro.setComentarios(new ArrayList<>());
        foro.getComentarios().add(comentario);
        return ResponseEntity.ok(foroRepository.save(foro));
    }

    @PatchMapping("/{id}/cerrar")
    public ResponseEntity<Foro> cerrar(@PathVariable String id) {
        Foro foro = foroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Foro no encontrado"));
        foro.setAbierto(false);
        return ResponseEntity.ok(foroRepository.save(foro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        foroRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
