package com.velacuss.backend.service.impl;

import com.velacuss.backend.dao.RolDao;
import com.velacuss.backend.domain.dto.RolDTO;
import com.velacuss.backend.mapper.RolMapper;
import com.velacuss.backend.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RolServiceImpl implements RolService {

    @Autowired
    private RolDao rolDao;
    @Autowired
    private RolMapper rolMapper;

    @Override
    public RolDTO obtenerRolPorUsuario(Long usuarioId) {
        return rolMapper.toDto(rolDao.obtenerRolPorUsuario(usuarioId));
    }

    @Override
    public List<RolDTO> listarRoles() {
        return rolMapper.toDto(rolDao.obtenerRoles());
    }
}

