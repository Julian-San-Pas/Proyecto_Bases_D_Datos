package com.centro.api.service;

import com.centro.api.model.Actividad;
import com.centro.api.model.Inscripcion;
import com.centro.api.repository.ActividadRepository;
import com.centro.api.repository.InscripcionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActividadService {

    private final ActividadRepository actividadRepository;
    private final InscripcionRepository inscripcionRepository;

    public Actividad crear(Actividad actividad) {
        if (actividad.getFechaFinalizacion().isBefore(actividad.getFechaInicio())) {
            throw new RuntimeException("La fecha de finalización no puede ser antes de la fecha de inicio");
        }
        // Si la propone un líder comunitario, requiere aprobación
        if (actividad.isRequiereAprobacion()) {
            actividad.setAprobada(false);
        }
        return actividadRepository.save(actividad);
    }

    public List<Actividad> listarTodas() {
        return actividadRepository.findAll();
    }

    public Actividad buscarPorId(String id) {
        return actividadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada con id: " + id));
    }

    public List<Actividad> buscarPorCategoria(Actividad.CategoriaActividad categoria) {
        return actividadRepository.findByCategoria(categoria);
    }

    public List<Actividad> buscarPorEstado(Actividad.EstadoActividad estado) {
        return actividadRepository.findByEstado(estado);
    }

    public List<Actividad> buscarPorInstructor(String instructorId) {
        return actividadRepository.findByInstructorId(instructorId);
    }

    public List<Actividad> buscarPorRangoFechas(LocalDate inicio, LocalDate fin) {
        return actividadRepository.findByRangoFechas(inicio, fin);
    }

    public List<Actividad> buscarPorNombre(String nombre) {
        return actividadRepository.findByNombreContaining(nombre);
    }

    public List<Actividad> listarPendientesAprobacion() {
        return actividadRepository.findByAprobadaAndRequiereAprobacion(false, true);
    }

    public Actividad aprobar(String id, String observaciones) {
        Actividad actividad = buscarPorId(id);
        actividad.setAprobada(true);
        actividad.setObservacionesAprobacion(observaciones);
        return actividadRepository.save(actividad);
    }

    public Actividad cambiarEstado(String id, Actividad.EstadoActividad nuevoEstado) {
        Actividad actividad = buscarPorId(id);
        actividad.setEstado(nuevoEstado);
        return actividadRepository.save(actividad);
    }

    public Actividad actualizar(String id, Actividad actividadActualizada) {
        Actividad actividad = buscarPorId(id);
        actividad.setNombre(actividadActualizada.getNombre());
        actividad.setDescripcion(actividadActualizada.getDescripcion());
        actividad.setObjetivo(actividadActualizada.getObjetivo());
        actividad.setCategoria(actividadActualizada.getCategoria());
        actividad.setFechaInicio(actividadActualizada.getFechaInicio());
        actividad.setFechaFinalizacion(actividadActualizada.getFechaFinalizacion());
        actividad.setIntensidadHoraria(actividadActualizada.getIntensidadHoraria());
        actividad.setCupoMaximo(actividadActualizada.getCupoMaximo());
        actividad.setRecursosRequeridos(actividadActualizada.getRecursosRequeridos());
        actividad.setInstructorId(actividadActualizada.getInstructorId());
        return actividadRepository.save(actividad);
    }

    public void eliminar(String id) {
        buscarPorId(id); // Verifica que exista
        actividadRepository.deleteById(id);
    }

    public int obtenerCuposDisponibles(String actividadId) {
        Actividad actividad = buscarPorId(actividadId);
        long inscritosActivos = inscripcionRepository.countByActividadIdAndEstado(
                actividadId, Inscripcion.EstadoInscripcion.ACTIVA);
        return actividad.getCupoMaximo() - (int) inscritosActivos;
    }
}
