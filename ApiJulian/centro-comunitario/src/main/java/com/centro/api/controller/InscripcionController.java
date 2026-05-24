package com.centro.api.controller;

import com.centro.api.model.Inscripcion;
import com.centro.api.service.InscripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/inscripciones")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class InscripcionController {

    private final InscripcionService inscripcionService;

    @PostMapping
    public ResponseEntity<Inscripcion> inscribir(@RequestBody Map<String, String> body) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(inscripcionService.inscribir(body.get("participanteId"), body.get("actividadId")));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscripcion> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(inscripcionService.buscarPorId(id));
    }

    @GetMapping("/participante/{participanteId}")
    public ResponseEntity<List<Inscripcion>> porParticipante(@PathVariable String participanteId) {
        return ResponseEntity.ok(inscripcionService.listarPorParticipante(participanteId));
    }

    @GetMapping("/actividad/{actividadId}")
    public ResponseEntity<List<Inscripcion>> porActividad(@PathVariable String actividadId) {
        return ResponseEntity.ok(inscripcionService.listarPorActividad(actividadId));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Inscripcion> cancelar(@PathVariable String id) {
        return ResponseEntity.ok(inscripcionService.cancelar(id));
    }
}
