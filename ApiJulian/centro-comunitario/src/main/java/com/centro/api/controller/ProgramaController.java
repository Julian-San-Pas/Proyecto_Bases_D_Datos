package com.centro.api.controller;

import com.centro.api.model.Programa;
import com.centro.api.repository.ProgramaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/programas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProgramaController {

    private final ProgramaRepository programaRepository;

    @PostMapping
    public ResponseEntity<Programa> crear(@Valid @RequestBody Programa programa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(programaRepository.save(programa));
    }

    @GetMapping
    public ResponseEntity<List<Programa>> listar() {
        return ResponseEntity.ok(programaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Programa> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(programaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Programa no encontrado")));
    }

    @PatchMapping("/{id}/inscribir")
    public ResponseEntity<Programa> inscribir(@PathVariable String id, @RequestBody Map<String, String> body) {
        Programa programa = programaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Programa no encontrado"));
        String participanteId = body.get("participanteId");
        if (programa.getParticipantesIds() != null && !programa.getParticipantesIds().contains(participanteId)) {
            programa.getParticipantesIds().add(participanteId);
        }
        return ResponseEntity.ok(programaRepository.save(programa));
    }

    @PatchMapping("/{id}/actividades")
    public ResponseEntity<Programa> agregarActividad(@PathVariable String id, @RequestBody Map<String, String> body) {
        Programa programa = programaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Programa no encontrado"));
        String actividadId = body.get("actividadId");
        if (programa.getActividadesIds() != null && !programa.getActividadesIds().contains(actividadId)) {
            programa.getActividadesIds().add(actividadId);
        }
        return ResponseEntity.ok(programaRepository.save(programa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Programa> actualizar(@PathVariable String id, @Valid @RequestBody Programa programa) {
        programaRepository.findById(id).orElseThrow(() -> new RuntimeException("Programa no encontrado"));
        programa.setId(id);
        return ResponseEntity.ok(programaRepository.save(programa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        programaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
