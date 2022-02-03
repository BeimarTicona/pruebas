package com.velacuss.backend.dao.impl;

import com.velacuss.backend.dao.ArchivoUsuarioDao;
import com.velacuss.backend.domain.ArchivoUsuario;
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
public class ArchivoUsuarioDaoImpl implements ArchivoUsuarioDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("velacussJdbcTemplate")
    private JdbcTemplate velacussJdbcTemplate;

    @Override
    public ArchivoUsuario obtenerArchivoPorId(Long id) {
        String sql = "SELECT * FROM archivo_usuario WHERE id = ? AND estado = ?";
        try {
            return velacussJdbcTemplate.queryForObject(
                    sql,
                    new Object[]{id, EstadoEnum.ACTIVO.getValue()},
                    new BeanPropertyRowMapper<>(ArchivoUsuario.class)
            );
        } catch (EmptyResultDataAccessException e) {
            log.info("obtenerArchivoPorId(...), Empty result: ".concat(Objects.requireNonNull(e.getMessage())));
            return null;
        } catch (Exception e) {
            log.error("obtenerArchivoPorId(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }

    @Override
    public ArchivoUsuario crudArchivo(ArchivoUsuario archivo, Integer operacion) {
        try {
            String sql = "SELECT * FROM crud_archivo_usuario (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            return velacussJdbcTemplate.queryForObject(
                    sql,
                    new Object[]{
                            archivo.getId(),
                            archivo.getLogtransId(),
                            archivo.getUsuarioId(),
                            archivo.getUrl(),
                            archivo.getNombre(),
                            archivo.getMimetype(),
                            archivo.getTamanio(),
                            archivo.getPrincipal(),
                            archivo.getOrden(),
                            archivo.getFecha(),
                            operacion
                    },
                    new BeanPropertyRowMapper<>(ArchivoUsuario.class)
            );
        } catch (EmptyResultDataAccessException dataAccessException) {
            log.error("crudArchivo(...), EmptyResult, mensaje del error: ".concat(Objects.requireNonNull(dataAccessException.getMessage())));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        } catch (Exception e) {
            log.error("crudArchivo(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }

    @Override
    public ArchivoUsuario obtenerArchivoPorUsuario(Long usuarioId) {
        try {
            String sql = "select * " +
                    "from archivo_usuario " +
                    "where usuario_id = ? and estado = ?;";
            return velacussJdbcTemplate.queryForObject(
                    sql,
                    new Object[]{usuarioId, EstadoEnum.ACTIVO.getValue()},
                    new BeanPropertyRowMapper<>(ArchivoUsuario.class));
        } catch (EmptyResultDataAccessException e) {
            log.info("obtenerArchivoPorUsuario(...), Empty result: ".concat(Objects.requireNonNull(e.getMessage())));
            return null;
        } catch (Exception e) {
            log.error("obtenerArchivoPorUsuario(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }

    @Override
    public List<ArchivoUsuario> listar() {
        try {
            String sql = "select * from archivo_usuario where estado = ?";
            return velacussJdbcTemplate.query(
                    sql,
                    new Object[]{EstadoEnum.ACTIVO.getValue()},
                    new BeanPropertyRowMapper<>(ArchivoUsuario.class));
        } catch (Exception e) {
            log.error("listar(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }

    
}
