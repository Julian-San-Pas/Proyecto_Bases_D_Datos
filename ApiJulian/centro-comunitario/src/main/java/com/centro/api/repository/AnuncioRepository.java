package com.centro.api.repository;

import com.centro.api.model.Anuncio;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AnuncioRepository extends MongoRepository<Anuncio, String> {
    List<Anuncio> findByPublicado(boolean publicado);
    List<Anuncio> findByAutorId(String autorId);
    List<Anuncio> findByActividadId(String actividadId);
    List<Anuncio> findByProgramaId(String programaId);
}
