package com.centro.api.service;

import com.centro.api.model.Usuario;
import com.centro.api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario crear(Usuario usuario) {
        if (usuarioRepository.existsByCorreoElectronico(usuario.getCorreoElectronico())) {
            throw new RuntimeException("El correo electrónico ya está registrado");
        }
        if (usuarioRepository.existsByDocumentoIdentificacion(usuario.getDocumentoIdentificacion())) {
            throw new RuntimeException("El documento de identificación ya está registrado");
        }
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(String id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    public Usuario buscarPorCorreo(String correo) {
        return usuarioRepository.findByCorreoElectronico(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con correo: " + correo));
    }

    public List<Usuario> buscarPorRol(Usuario.RolUsuario rol) {
        return usuarioRepository.findByRol(rol);
    }

    public Usuario actualizar(String id, Usuario usuarioActualizado) {
        Usuario usuario = buscarPorId(id);
        usuario.setNombreCompleto(usuarioActualizado.getNombreCompleto());
        usuario.setEdad(usuarioActualizado.getEdad());
        usuario.setNumerTelefono(usuarioActualizado.getNumerTelefono());
        usuario.setDireccionResidencia(usuarioActualizado.getDireccionResidencia());
        if (usuarioActualizado.getContrasena() != null && !usuarioActualizado.getContrasena().isBlank()) {
            usuario.setContrasena(passwordEncoder.encode(usuarioActualizado.getContrasena()));
        }
        return usuarioRepository.save(usuario);
    }

    public void desactivar(String id) {
        Usuario usuario = buscarPorId(id);
        usuario.setActivo(false);
        usuarioRepository.save(usuario);
    }

    public List<Usuario> listarActivos() {
        return usuarioRepository.findByActivo(true);
    }
}
