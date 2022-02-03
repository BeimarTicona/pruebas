package com.velacuss.backend.dao.impl;

import com.velacuss.backend.dao.LineaDao;
import com.velacuss.backend.domain.Linea;
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
public class LineaDaoImpl implements LineaDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("velacussJdbcTemplate")
    private JdbcTemplate velacussJdbcTemplate;

    @Override
    public Linea obtenerLineaPorId(Long id) {
        try {
            String sql = "SELECT * FROM linea WHERE id = ? AND estado = ?";
            return velacussJdbcTemplate.queryForObject(
                    sql,
                    new Object[]{id, EstadoEnum.ACTIVO.getValue()},
                    new BeanPropertyRowMapper<>(Linea.class)
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
    public Boolean existeLinea(String nombre) {
        try {
            String sql = "SELECT COUNT(*) FROM linea WHERE nombre = ? AND estado = ?";

            int count = velacussJdbcTemplate.queryForObject(sql,
                    new Object[] { nombre, EstadoEnum.ACTIVO.getValue() }, Integer.class);
            if (count >= 1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("existeMarca(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }

    @Override
    public Linea crudLinea(Linea linea, Integer operacion) {
        try {
            String sql = "select * from crud_linea(?, ?, ?, ?)";
            return velacussJdbcTemplate.queryForObject(
                    sql,
                    new Object[]{
                            linea.getId(),
                            linea.getLogtransId(),
                            linea.getNombre(),
                            operacion
                    },
                    new BeanPropertyRowMapper<>(Linea.class)
            );
        } catch (EmptyResultDataAccessException dataAccessException) {
            log.error("crud(...), EmptyResult, mensaje del error: ".concat(Objects.requireNonNull(dataAccessException.getMessage())));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        } catch (Exception e) {
            log.error("crud(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }

    @Override
    public List<Linea> listarLineas() {
        try {
            String sql = "select * from linea where estado = ? order by nombre;";
            return velacussJdbcTemplate.query(
                    sql,
                    new Object[]{EstadoEnum.ACTIVO.getValue()},
                    new BeanPropertyRowMapper<>(Linea.class));
        } catch (Exception e) {
            log.error("listarLineas(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }
}
