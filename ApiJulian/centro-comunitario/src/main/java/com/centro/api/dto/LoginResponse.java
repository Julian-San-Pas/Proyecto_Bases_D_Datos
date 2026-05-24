package com.centro.api.dto;

import com.centro.api.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token;
    @Builder.Default
    private String tipo = "Bearer";
    private String id;
    private String nombreCompleto;
    private String correoElectronico;
    private Usuario.RolUsuario rol;
}
