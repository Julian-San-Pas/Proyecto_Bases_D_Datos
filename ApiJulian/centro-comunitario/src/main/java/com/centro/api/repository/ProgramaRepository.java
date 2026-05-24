package com.centro.api.repository;

import com.centro.api.model.Programa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProgramaRepository extends MongoRepository<Programa, String> {
    List<Programa> findByActivo(boolean activo);
    List<Programa> findByResponsablesIdsContaining(String responsableId);
    List<Programa> findByParticipantesIdsContaining(String participanteId);
}
