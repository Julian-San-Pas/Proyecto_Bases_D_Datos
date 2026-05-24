package com.centro.api.repository;

import com.centro.api.model.Actividad;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ActividadRepository extends MongoRepository<Actividad, String> {

    List<Actividad> findByCategoria(Actividad.CategoriaActividad categoria);

    List<Actividad> findByEstado(Actividad.EstadoActividad estado);

    List<Actividad> findByInstructorId(String instructorId);

    List<Actividad> findByAprobadaAndRequiereAprobacion(boolean aprobada, boolean requiereAprobacion);

    @Query("{ 'fechaInicio': { $gte: ?0 }, 'fechaFinalizacion': { $lte: ?1 } }")
    List<Actividad> findByRangoFechas(LocalDate inicio, LocalDate fin);

    @Query("{ 'nombre': { $regex: ?0, $options: 'i' } }")
    List<Actividad> findByNombreContaining(String nombre);

    List<Actividad> findByCategoriaAndEstado(Actividad.CategoriaActividad categoria, Actividad.EstadoActividad estado);
}
