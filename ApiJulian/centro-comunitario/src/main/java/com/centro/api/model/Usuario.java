package com.centro.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String id;

    @NotBlank(message = "El nombre completo es obligatorio")
    private String nombreCompleto;

    @NotBlank(message = "El documento de identificación es obligatorio")
    @Indexed(unique = true)
    private String documentoIdentificacion;

    @Min(value = 5, message = "La edad mínima es 5 años")
    @Max(value = 120, message = "La edad máxima es 120 años")
    private int edad;

    @Email(message = "El correo electrónico no es válido")
    @NotBlank(message = "El correo electrónico es obligatorio")
    @Indexed(unique = true)
    private String correoElectronico;

    @NotBlank(message = "El número de teléfono es obligatorio")
    private String numerTelefono;

    @NotBlank(message = "La dirección de residencia es obligatoria")
    private String direccionResidencia;

    @NotNull(message = "El rol es obligatorio")
    private RolUsuario rol;

    @NotBlank(message = "La contraseña es obligatoria")
    private String contrasena;

    private boolean activo = true;

    @CreatedDate
    private LocalDateTime fechaCreacion;

    @LastModifiedDate
    private LocalDateTime fechaActualizacion;

    public enum RolUsuario {
        PARTICIPANTE,
        INSTRUCTOR,
        COORDINADOR,
        ADMINISTRADOR
    }
}
