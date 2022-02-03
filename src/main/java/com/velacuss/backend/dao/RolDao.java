package com.velacuss.backend.dao;

import com.velacuss.backend.domain.Rol;
import com.velacuss.backend.domain.dto.RolDTO;

import java.util.List;

public interface RolDao {
    List<Rol> obtenerRoles();
    Rol obtenerRolPorUsuario(Long usurioId);
    RolDTO obtenerRolDTOPorUsuario(Long usurioId);
}
