package com.velacuss.backend.dao;

import com.velacuss.backend.domain.UsuarioRol;

import java.util.List;

public interface UsuarioRolDao {
    UsuarioRol obtenerUsuarioRolPorId(Long id);
    UsuarioRol obtenerUsuarioRolPorUsuario(Long usuarioId);
    UsuarioRol crudUsuarioRol(UsuarioRol usuario, Integer operacion);
    List<UsuarioRol> listarRoles();
}
