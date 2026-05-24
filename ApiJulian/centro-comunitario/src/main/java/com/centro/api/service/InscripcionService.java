package com.centro.api.service;

import com.centro.api.model.Actividad;
import com.centro.api.model.Inscripcion;
import com.centro.api.repository.ActividadRepository;
import com.centro.api.repository.InscripcionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final ActividadRepository actividadRepository;
    private final ActividadService actividadService;

    public Inscripcion inscribir(String participanteId, String actividadId) {
        // Verificar que la actividad existe y está activa
        Actividad actividad = actividadRepository.findById(actividadId)
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));

        if (actividad.getEstado() == Actividad.EstadoActividad.CANCELADA ||
                actividad.getEstado() == Actividad.EstadoActividad.FINALIZADA) {
            throw new RuntimeException("No se puede inscribir en una actividad cancelada o finalizada");
        }

        // Verificar que no esté ya inscrito
        if (inscripcionRepository.existsByParticipanteIdAndActividadIdAndEstado(
                participanteId, actividadId, Inscripcion.EstadoInscripcion.ACTIVA)) {
            throw new RuntimeException("El participante ya está inscrito en esta actividad");
        }

        // Verificar cupos disponibles
        int cuposDisponibles = actividadService.obtenerCuposDisponibles(actividadId);

        Inscripcion inscripcion = Inscripcion.builder()
                .participanteId(participanteId)
                .actividadId(actividadId)
                .build();

        if (cuposDisponibles <= 0) {
            inscripcion.setEstado(Inscripcion.EstadoInscripcion.LISTA_ESPERA);
        }

        return inscripcionRepository.save(inscripcion);
    }

    public Inscripcion cancelar(String inscripcionId) {
        Inscripcion inscripcion = inscripcionRepository.findById(inscripcionId)
                .orElseThrow(() -> new RuntimeException("Inscripción no encontrada"));
        inscripcion.setEstado(Inscripcion.EstadoInscripcion.CANCELADA);
        return inscripcionRepository.save(inscripcion);
    }

    public List<Inscripcion> listarPorParticipante(String participanteId) {
        return inscripcionRepository.findByParticipanteId(participanteId);
    }

    public List<Inscripcion> listarPorActividad(String actividadId) {
        return inscripcionRepository.findByActividadId(actividadId);
    }

    public Inscripcion buscarPorId(String id) {
        return inscripcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscripción no encontrada"));
    }
}
