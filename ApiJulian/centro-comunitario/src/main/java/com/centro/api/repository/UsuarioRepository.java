package com.centro.api.repository;

import com.centro.api.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByCorreoElectronico(String correoElectronico);
    Optional<Usuario> findByDocumentoIdentificacion(String documentoIdentificacion);
    List<Usuario> findByRol(Usuario.RolUsuario rol);
    List<Usuario> findByActivo(boolean activo);
    boolean existsByCorreoElectronico(String correoElectronico);
    boolean existsByDocumentoIdentificacion(String documentoIdentificacion);
}
