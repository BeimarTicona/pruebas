package com.velacuss.backend.service;

import com.velacuss.backend.domain.dto.RolDTO;

import java.util.List;

public interface RolService {
    RolDTO obtenerRolPorUsuario(Long usuarioId);
    List<RolDTO> listarRoles();
}
