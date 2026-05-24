package com.centro.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "actividades")
public class Actividad {

    @Id
    private String id;

    @NotBlank(message = "El nombre de la actividad es obligatorio")
    private String nombre;

    @NotNull(message = "La categoría es obligatoria")
    private CategoriaActividad categoria;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotBlank(message = "El objetivo es obligatorio")
    private String objetivo;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de finalización es obligatoria")
    private LocalDate fechaFinalizacion;

    @Positive(message = "La intensidad horaria debe ser positiva")
    private int intensidadHoraria;

    @Positive(message = "El cupo máximo debe ser positivo")
    private int cupoMaximo;

    @Builder.Default
    private EstadoActividad estado = EstadoActividad.PROGRAMADA;

    private List<String> recursosRequeridos;

    // ID del instructor asignado
    private String instructorId;

    // ID del proponente (puede ser staff o líder comunitario)
    private String proponenteId;

    // Si fue propuesta por un líder comunitario, requiere aprobación
    @Builder.Default
    private boolean requiereAprobacion = false;

    @Builder.Default
    private boolean aprobada = true;

    private String observacionesAprobacion;

    @CreatedDate
    private LocalDateTime fechaCreacion;

    @LastModifiedDate
    private LocalDateTime fechaActualizacion;

    public enum CategoriaActividad {
        ARTE,
        DEPORTE,
        TECNOLOGIA,
        SALUD,
        EMPRENDIMIENTO,
        DESARROLLO_PERSONAL
    }

    public enum EstadoActividad {
        PROGRAMADA,
        EN_CURSO,
        FINALIZADA,
        CANCELADA
    }
}
