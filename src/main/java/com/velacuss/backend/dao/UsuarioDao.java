package com.velacuss.backend.dao;

import com.velacuss.backend.domain.Usuario;

import java.util.List;

public interface UsuarioDao {
    Usuario obtenerUsuarioPorLogin(String login);
    Usuario obtenerUsuarioPorFuncionario(Long funcionarioId);
    Usuario obtenerUsuarioPorId(Long id);
    Usuario crudUsuario(Usuario usuario, Integer operacion);
    List<Usuario> listarUsuario();
    Boolean existeUsuariosPorSurcusal(Long sucursalId);
    Boolean existeUsuarioPorLogin(String login);
}
