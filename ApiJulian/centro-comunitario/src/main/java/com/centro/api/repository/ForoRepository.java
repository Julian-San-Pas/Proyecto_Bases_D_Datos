package com.centro.api.repository;

import com.centro.api.model.Foro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ForoRepository extends MongoRepository<Foro, String> {
    List<Foro> findByAbierto(boolean abierto);
    List<Foro> findByCreadorId(String creadorId);
    List<Foro> findByActividadId(String actividadId);
    List<Foro> findByProgramaId(String programaId);
}
