package com.centro.api.repository;

import com.centro.api.model.Inscripcion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InscripcionRepository extends MongoRepository<Inscripcion, String> {
    List<Inscripcion> findByParticipanteId(String participanteId);
    List<Inscripcion> findByActividadId(String actividadId);
    Optional<Inscripcion> findByParticipanteIdAndActividadId(String participanteId, String actividadId);
    long countByActividadIdAndEstado(String actividadId, Inscripcion.EstadoInscripcion estado);
    boolean existsByParticipanteIdAndActividadIdAndEstado(String participanteId, String actividadId, Inscripcion.EstadoInscripcion estado);
    List<Inscripcion> findByActividadIdAndEstado(String actividadId, Inscripcion.EstadoInscripcion estado);
}
