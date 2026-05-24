package com.centro.api.repository;

import com.centro.api.model.Sesion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SesionRepository extends MongoRepository<Sesion, String> {
    List<Sesion> findByActividadId(String actividadId);
    List<Sesion> findByFecha(LocalDate fecha);
    List<Sesion> findByFechaGreaterThanEqual(LocalDate fecha);
    List<Sesion> findByModalidad(Sesion.Modalidad modalidad);
}
