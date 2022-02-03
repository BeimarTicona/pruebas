package com.velacuss.backend.dao.impl;

import com.velacuss.backend.dao.RolDao;
import com.velacuss.backend.domain.Rol;
import com.velacuss.backend.domain.dto.RolDTO;
import com.velacuss.backend.enums.EstadoEnum;
import com.velacuss.backend.exceptions.InternalServerErrorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Transactional
@Repository
public class RolDaoImpl implements RolDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("velacussJdbcTemplate")
    JdbcTemplate velacussJdbcTemplate;

    @Override
    public List<Rol> obtenerRoles() {
        try {
            String sql = "select * from rol r where estado = ?;";
            return velacussJdbcTemplate.query(
                    sql,
                    new Object[]{EstadoEnum.ACTIVO.getValue()},
                    new BeanPropertyRowMapper<>(Rol.class)
            );
        } catch (Exception e) {
            log.error("listarLineas(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }

    @Override
    public Rol obtenerRolPorUsuario(Long usurioId) {
        try {
            String sql = "select r.* from rol r, usuario_rol ur where r.id = ur.rol_id and ur.usuario_id = ? and r.estado = 'AC' and ur.estado = 'AC';";
            return velacussJdbcTemplate.queryForObject(
                    sql,
                    new Object[]{usurioId},
                    new BeanPropertyRowMapper<>(Rol.class)
            );
        } catch (EmptyResultDataAccessException e) {
            log.info("obtenerLineaPorId(...), Empty result: ".concat(Objects.requireNonNull(e.getMessage())));
            return null;
        } catch (Exception e) {
            log.error("obtenerLineaPorId(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }
    @Override
    public RolDTO obtenerRolDTOPorUsuario(Long usurioId) {
        try {
            String sql = "select r.* from rol r, usuario_rol ur where r.id = ur.rol_id and ur.usuario_id = ? and r.estado = 'AC' and ur.estado = 'AC';";
            return velacussJdbcTemplate.queryForObject(
                    sql,
                    new Object[]{usurioId},
                    new BeanPropertyRowMapper<>(RolDTO.class)
            );
        } catch (EmptyResultDataAccessException e) {
            log.info("obtenerLineaPorId(...), Empty result: ".concat(Objects.requireNonNull(e.getMessage())));
            return null;
        } catch (Exception e) {
            log.error("obtenerLineaPorId(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }
}
