package com.centro.api.controller;

import com.centro.api.model.Sesion;
import com.centro.api.repository.SesionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/sesiones")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SesionController {

    private final SesionRepository sesionRepository;

    @PostMapping
    public ResponseEntity<Sesion> crear(@Valid @RequestBody Sesion sesion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sesionRepository.save(sesion));
    }

    @GetMapping
    public ResponseEntity<List<Sesion>> listar() {
        return ResponseEntity.ok(sesionRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sesion> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(sesionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sesión no encontrada")));
    }

    @GetMapping("/actividad/{actividadId}")
    public ResponseEntity<List<Sesion>> porActividad(@PathVariable String actividadId) {
        return ResponseEntity.ok(sesionRepository.findByActividadId(actividadId));
    }

    @PatchMapping("/{id}/asistencia")
    public ResponseEntity<Sesion> registrarAsistencia(@PathVariable String id,
                                                       @RequestBody Map<String, List<String>> body) {
        Sesion sesion = sesionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sesión no encontrada"));
        sesion.setParticipantesAsistentes(body.get("participantesAsistentes"));
        return ResponseEntity.ok(sesionRepository.save(sesion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sesion> actualizar(@PathVariable String id, @Valid @RequestBody Sesion sesion) {
        sesionRepository.findById(id).orElseThrow(() -> new RuntimeException("Sesión no encontrada"));
        sesion.setId(id);
        return ResponseEntity.ok(sesionRepository.save(sesion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        sesionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
