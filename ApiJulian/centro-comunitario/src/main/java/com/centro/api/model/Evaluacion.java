package com.centro.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "evaluaciones")
public class Evaluacion {

    @Id
    private String id;

    @NotBlank(message = "El ID del autor es obligatorio")
    private String autorId;

    private String actividadId;
    private String sesionId;
    private String participanteEvaluadoId;

    @NotNull(message = "El tipo de evaluación es obligatorio")
    private TipoEvaluacion tipo;

    @Min(value = 1, message = "La valoración mínima es 1")
    @Max(value = 5, message = "La valoración máxima es 5")
    private int valoracion;

    @NotBlank(message = "Las observaciones son obligatorias")
    private String observaciones;

    private String sugerenciasMejora;
    private String comentarioDesempeno;
    private String nivelCompromiso;
    private String progresoObservado;

    @CreatedDate
    private LocalDateTime fechaCreacion;

    public enum TipoEvaluacion {
        PARTICIPANTE_SOBRE_ACTIVIDAD,
        PARTICIPANTE_SOBRE_SESION,
        INSTRUCTOR_SOBRE_PARTICIPANTE
    }
}
