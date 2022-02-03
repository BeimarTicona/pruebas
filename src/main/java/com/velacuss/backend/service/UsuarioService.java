package com.velacuss.backend.service;

import com.velacuss.backend.domain.dto.UsuarioDTO;
import com.velacuss.backend.domain.dto.UsuarioInicioDTO;

import java.util.List;

public interface UsuarioService {
    UsuarioInicioDTO inicio();
    UsuarioDTO guardar(UsuarioDTO usuarioDTO);
    UsuarioDTO obtener(Long id);
    UsuarioDTO editar(UsuarioDTO usuarioDTO);
    void eliminar(Long id);
    List<UsuarioDTO> listarUsuarios();
    UsuarioDTO actualizarRol(UsuarioDTO usuarioDTO);
    UsuarioDTO actualizarSucursal(UsuarioDTO usuarioDTO);
    UsuarioDTO actualizarEstado(UsuarioDTO usuarioDTO);

    UsuarioDTO obtenerUsuarioPorLogin(String login);
}
