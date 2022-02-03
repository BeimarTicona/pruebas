package com.velacuss.backend.dao.impl;

import com.velacuss.backend.dao.PersonaDao;
import com.velacuss.backend.domain.Persona;
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
public class PersonaDaoImpl implements PersonaDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("velacussJdbcTemplate")
    private JdbcTemplate velacussJdbcTemplate;

    @Override
    public Persona obtenerPersonaPorId(Long PersonaId) {
        try {
            String sql = "SELECT * FROM personas WHERE id = ? AND estado = 'AC'";
            return velacussJdbcTemplate.queryForObject(
                    sql,
                    new Object[]{PersonaId},
                    new BeanPropertyRowMapper<>(Persona.class)
            );
        } catch (EmptyResultDataAccessException e) {
            log.info("obtenerPersonaPorId(...), Empty result: ".concat(Objects.requireNonNull(e.getMessage())));
            return null;
        } catch (Exception e) {
            log.error("obtenerPersonaPorId(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }

    @Override
    public Persona obtenerPersonaPorUsuario(Long id) {
        try {
            String sql = "SELECT * FROM personas WHERE usuario_id = ? AND estado = 'AC'";
            return velacussJdbcTemplate.queryForObject(
                    sql,
                    new Object[]{id},
                    new BeanPropertyRowMapper<>(Persona.class)
            );
        } catch (EmptyResultDataAccessException e) {
            log.info("obtenerUsuarioPorId(...), Empty result: ".concat(Objects.requireNonNull(e.getMessage())));
            return null;
        } catch (Exception e) {
            log.error("obtenerUsuarioPorId(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }

    @Override
    public Persona crudPersona(Persona persona, Integer operacion) {
        try {
            String sql = "select * from crud_persona(?, ?, ?, ?, ?, ?)";
            return velacussJdbcTemplate.queryForObject(
                    sql,
                    new Object[]{
                            persona.getId(),
                            persona.getLogtransId(),
                            persona.getUsuarioId(),
                            persona.getNombre(),
                            persona.getApellido(),
                            operacion
                    },
                    new BeanPropertyRowMapper<>(Persona.class)
            );
        } catch (EmptyResultDataAccessException dataAccessException) {
            log.error("crudUsuario(...), EmptyResult, mensaje del error: ".concat(Objects.requireNonNull(dataAccessException.getMessage())));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        } catch (Exception e) {
            log.error("crudUsuario(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }

    @Override
    public List<Persona> listarPersona() {
        try {
            String sql = "SELECT * FROM personas WHERE estado = 'AC' order by id asc;";
            return velacussJdbcTemplate.query(
                    sql,
                    new BeanPropertyRowMapper<>(Persona.class)
            );
        } catch (Exception e) {
            log.error("listarUsuario(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }

}
