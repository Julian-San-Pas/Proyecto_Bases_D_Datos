package com.centro.api.controller;

import com.centro.api.model.Evaluacion;
import com.centro.api.repository.EvaluacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/evaluaciones")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EvaluacionController {

    private final EvaluacionRepository evaluacionRepository;

    @PostMapping
    public ResponseEntity<Evaluacion> crear(@Valid @RequestBody Evaluacion evaluacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(evaluacionRepository.save(evaluacion));
    }

    @GetMapping
    public ResponseEntity<List<Evaluacion>> listar() {
        return ResponseEntity.ok(evaluacionRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evaluacion> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(evaluacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluación no encontrada")));
    }

    @GetMapping("/actividad/{actividadId}")
    public ResponseEntity<List<Evaluacion>> porActividad(@PathVariable String actividadId) {
        return ResponseEntity.ok(evaluacionRepository.findByActividadId(actividadId));
    }

    @GetMapping("/participante/{participanteId}")
    public ResponseEntity<List<Evaluacion>> porParticipante(@PathVariable String participanteId) {
        return ResponseEntity.ok(evaluacionRepository.findByParticipanteEvaluadoId(participanteId));
    }

    @GetMapping("/autor/{autorId}")
    public ResponseEntity<List<Evaluacion>> porAutor(@PathVariable String autorId) {
        return ResponseEntity.ok(evaluacionRepository.findByAutorId(autorId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        evaluacionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
