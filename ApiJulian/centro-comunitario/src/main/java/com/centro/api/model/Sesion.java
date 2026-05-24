package com.centro.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "sesiones")
public class Sesion {

    @Id
    private String id;

    @NotBlank(message = "El ID de la actividad es obligatorio")
    private String actividadId;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    @NotNull(message = "La hora de inicio es obligatoria")
    private LocalTime horaInicio;

    @NotNull(message = "La hora de finalización es obligatoria")
    private LocalTime horaFinalizacion;

    @NotNull(message = "La modalidad es obligatoria")
    private Modalidad modalidad;

    private String espacioAsignado;
    private String enlaceAcceso;

    private List<String> participantesConvocados;
    private List<String> participantesAsistentes;

    private List<ArchivoAdjunto> archivosAdjuntos;
    private String observaciones;

    @CreatedDate
    private LocalDateTime fechaCreacion;

    @LastModifiedDate
    private LocalDateTime fechaActualizacion;

    public enum Modalidad {
        PRESENCIAL, VIRTUAL, HIBRIDA
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ArchivoAdjunto {
        private String nombre;
        private String url;
        private String tipo;
        private long tamanoBytes;
    }
}
