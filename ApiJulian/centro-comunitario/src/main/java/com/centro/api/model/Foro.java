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
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "foros")
public class Foro {

    @Id
    private String id;

    @NotBlank(message = "El título del tema es obligatorio")
    private String titulo;

    @NotBlank(message = "La descripción inicial es obligatoria")
    private String descripcionInicial;

    @NotBlank(message = "El ID del creador es obligatorio")
    private String creadorId;

    private String actividadId;
    private String programaId;

    @Builder.Default
    private boolean abierto = true;

    private List<Comentario> comentarios;
    private List<String> archivosCompartidos;

    @CreatedDate
    private LocalDateTime fechaCreacion;

    @LastModifiedDate
    private LocalDateTime fechaActualizacion;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Comentario {
        private String id;
        private String autorId;
        private String contenido;
        private LocalDateTime fechaComentario;
        private List<String> archivosAdjuntos;
    }
}
