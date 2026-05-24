package com.centro.api.repository;

import com.centro.api.model.Evaluacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EvaluacionRepository extends MongoRepository<Evaluacion, String> {
    List<Evaluacion> findByActividadId(String actividadId);
    List<Evaluacion> findBySesionId(String sesionId);
    List<Evaluacion> findByAutorId(String autorId);
    List<Evaluacion> findByParticipanteEvaluadoId(String participanteEvaluadoId);
    List<Evaluacion> findByTipo(Evaluacion.TipoEvaluacion tipo);
}
