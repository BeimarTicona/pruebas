package com.velacuss.backend.service;

import com.velacuss.backend.domain.dto.FuncionarioDTO;

import java.util.List;

public interface FuncionarioService {
    FuncionarioDTO guardar(FuncionarioDTO funcionarioDTO);
    FuncionarioDTO obtener(Long id);
    FuncionarioDTO editar(FuncionarioDTO funcionarioDTO);
    void eliminar(Long id);
    List<FuncionarioDTO> listaFuncionarios();
    FuncionarioDTO obtenerFuncionarioPorLogin(String userName);
    List<FuncionarioDTO> listaFuncionariosVendedores();
}
