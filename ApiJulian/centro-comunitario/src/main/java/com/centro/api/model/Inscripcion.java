package com.centro.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "inscripciones")
public class Inscripcion {

    @Id
    private String id;

    @NotBlank(message = "El ID del participante es obligatorio")
    private String participanteId;

    @NotBlank(message = "El ID de la actividad es obligatorio")
    private String actividadId;

    @Builder.Default
    private LocalDateTime fechaInscripcion = LocalDateTime.now();

    @Builder.Default
    private EstadoInscripcion estado = EstadoInscripcion.ACTIVA;

    private String observaciones;

    @CreatedDate
    private LocalDateTime fechaCreacion;

    public enum EstadoInscripcion {
        ACTIVA, CANCELADA, COMPLETADA, LISTA_ESPERA
    }
}
