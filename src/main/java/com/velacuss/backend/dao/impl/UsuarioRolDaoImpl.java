package com.velacuss.backend.dao.impl;

import com.velacuss.backend.dao.UsuarioRolDao;
import com.velacuss.backend.domain.UsuarioRol;
import com.velacuss.backend.exceptions.InternalServerErrorException;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Transactional
@Repository
public class UsuarioRolDaoImpl implements UsuarioRolDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("velacussJdbcTemplate")
    private JdbcTemplate velacussJdbcTemplate;

    @Override
    public UsuarioRol obtenerUsuarioRolPorId(Long id) {
        try {
            String sql = "SELECT * FROM usuario_rol WHERE id = ? AND estado = 'AC'";
            return velacussJdbcTemplate.queryForObject(
                    sql,
                    new Object[]{id},
                    new BeanPropertyRowMapper<>(UsuarioRol.class)
            );
        } catch (EmptyResultDataAccessException e) {
            log.info("obtenerUsuarioRolPorId(...), Empty result: ".concat(Objects.requireNonNull(e.getMessage())));
            return null;
        } catch (Exception e) {
            log.error("obtenerUsuarioRolPorId(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }

    @Override
    public UsuarioRol obtenerUsuarioRolPorUsuario(Long usuarioId) {
        try {
            String sql = "SELECT * FROM usuario_rol WHERE usuario_id = ? AND estado = 'AC'";
            return velacussJdbcTemplate.queryForObject(
                    sql,
                    new Object[]{usuarioId},
                    new BeanPropertyRowMapper<>(UsuarioRol.class)
            );
        } catch (EmptyResultDataAccessException e) {
            log.info("obtenerUsuarioRolPorUsuario(...), Empty result: ".concat(Objects.requireNonNull(e.getMessage())));
            return null;
        } catch (Exception e) {
            log.error("obtenerUsuarioRolPorUsuario(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }

    @Override
    public UsuarioRol crudUsuarioRol(UsuarioRol usuario, Integer operacion) {
        try {
            String sql = "select * from crud_usuario_rol(?, ?, ?, ?)";
            return velacussJdbcTemplate.queryForObject(
                    sql,
                    new Object[]{
                            usuario.getId(),
                            usuario.getUsuarioId(),
                            usuario.getRolId(),
                            operacion
                    },
                    new BeanPropertyRowMapper<>(UsuarioRol.class)
            );
        } catch (EmptyResultDataAccessException dataAccessException) {
            log.error("crudUsuarioRol(...), EmptyResult, mensaje del error: ".concat(Objects.requireNonNull(dataAccessException.getMessage())));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        } catch (Exception e) {
            log.error("crudUsuarioRol(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }

    @Override
    public List<UsuarioRol> listarRoles() {
        try {
            String sql = "SELECT * FROM usuario_rol WHERE estado = 'AC'";
            return velacussJdbcTemplate.query(
                    sql,
                    new BeanPropertyRowMapper<>(UsuarioRol.class)
            );
        } catch (Exception e) {
            log.error("listarRoles(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }
}
