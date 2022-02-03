package com.velacuss.backend.dao.impl;

import com.velacuss.backend.dao.SistemaFechaDao;
import com.velacuss.backend.exceptions.InternalServerErrorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;


@Transactional
@Repository
public class SistemaFechaDaoImpl implements SistemaFechaDao {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("velacussJdbcTemplate")
    private JdbcTemplate velacussJdbcTemplate;

    @Override
    public LocalDateTime obtenerFechaHoraServidor() {
        try {
            String sql = "select (cast(fecha as date) || ' ' || to_char(current_timestamp , 'HH24:MI:SS'))::timestamp " +
                        "from sistema_fecha " +
                        "where estado='AC';";
            return velacussJdbcTemplate.queryForObject(
                    sql,
                    LocalDateTime.class
                    );
        } catch (EmptyResultDataAccessException e) {
            log.info("obtenerFechaHoraServidor(...), Empty result: ".concat(Objects.requireNonNull(e.getMessage())));
            return null;
        } catch (Exception e) {
            log.error("obtenerFechaHoraServidor(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }

    @Override
    public LocalDate obtenerFechaServidor() {
        try {
            String sql = "select (cast(fecha as date) || ' ' || to_char(current_timestamp , 'HH24:MI:SS'))::timestamp " +
                        "from sistema_fecha " +
                        "where estado='AC';";
            return velacussJdbcTemplate.queryForObject(
                    sql,
                    LocalDate.class
            );
        } catch (EmptyResultDataAccessException e) {
            log.info("obtenerFechaServidor(...), Empty result: ".concat(Objects.requireNonNull(e.getMessage())));
            return null;
        } catch (Exception e) {
            log.error("obtenerFechaServidor(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }
}
