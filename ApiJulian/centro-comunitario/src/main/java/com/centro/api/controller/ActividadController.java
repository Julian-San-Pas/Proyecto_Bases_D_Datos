package com.centro.api.controller;

import com.centro.api.model.Actividad;
import com.centro.api.service.ActividadService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/actividades")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ActividadController {

    private final ActividadService actividadService;

    @PostMapping
    public ResponseEntity<Actividad> crear(@Valid @RequestBody Actividad actividad) {
        return ResponseEntity.status(HttpStatus.CREATED).body(actividadService.crear(actividad));
    }

    @GetMapping
    public ResponseEntity<List<Actividad>> listar() {
        return ResponseEntity.ok(actividadService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actividad> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(actividadService.buscarPorId(id));
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Actividad>> buscarPorCategoria(@PathVariable Actividad.CategoriaActividad categoria) {
        return ResponseEntity.ok(actividadService.buscarPorCategoria(categoria));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Actividad>> buscarPorEstado(@PathVariable Actividad.EstadoActividad estado) {
        return ResponseEntity.ok(actividadService.buscarPorEstado(estado));
    }

    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<List<Actividad>> buscarPorInstructor(@PathVariable String instructorId) {
        return ResponseEntity.ok(actividadService.buscarPorInstructor(instructorId));
    }

    @GetMapping("/rango-fechas")
    public ResponseEntity<List<Actividad>> buscarPorRangoFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        return ResponseEntity.ok(actividadService.buscarPorRangoFechas(inicio, fin));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Actividad>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(actividadService.buscarPorNombre(nombre));
    }

    @GetMapping("/pendientes-aprobacion")
    public ResponseEntity<List<Actividad>> pendientesAprobacion() {
        return ResponseEntity.ok(actividadService.listarPendientesAprobacion());
    }

    @GetMapping("/{id}/cupos-disponibles")
    public ResponseEntity<Map<String, Integer>> cuposDisponibles(@PathVariable String id) {
        return ResponseEntity.ok(Map.of("cuposDisponibles", actividadService.obtenerCuposDisponibles(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Actividad> actualizar(@PathVariable String id, @Valid @RequestBody Actividad actividad) {
        return ResponseEntity.ok(actividadService.actualizar(id, actividad));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Actividad> cambiarEstado(@PathVariable String id, @RequestBody Map<String, String> body) {
        Actividad.EstadoActividad estado = Actividad.EstadoActividad.valueOf(body.get("estado"));
        return ResponseEntity.ok(actividadService.cambiarEstado(id, estado));
    }

    @PatchMapping("/{id}/aprobar")
    public ResponseEntity<Actividad> aprobar(@PathVariable String id, @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(actividadService.aprobar(id, body.get("observaciones")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        actividadService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
