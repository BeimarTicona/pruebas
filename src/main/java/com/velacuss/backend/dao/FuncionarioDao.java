package com.velacuss.backend.dao;

import com.velacuss.backend.domain.Funcionario;

import java.util.List;

public interface FuncionarioDao {
    Funcionario obtenerFuncionarioPorId(Long funcionarioId);

    Funcionario obtenerFuncionarioPorUsuario(String login);

    Funcionario crudFuncionario(Funcionario funcionario, Integer operacion);

    List<Funcionario> listarFuncionario();

    List<Funcionario> listarFuncionariosVendedores();

    String obtenerNombreCompletoFuncionario(Long funcionarioId);

    String obtenerNombreCompletoUsuario(Long usuarioId);
}
