package com.velacuss.backend.dao.impl;

import com.velacuss.backend.dao.DominioDao;
import com.velacuss.backend.domain.Dominio;
import com.velacuss.backend.domain.dto.DominioDTO;
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
public class DominioDaoImpl implements DominioDao {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("velacussJdbcTemplate")
    private JdbcTemplate velacussJdbcTemplate;

    @Override
    public List<DominioDTO> obtenerDominiosPorDominio(String criterio) {
        try {
            String sql = "select codigo, nombre, descripcion from dominio where dominio = ? and estado = ? order by orden, nombre";
            return velacussJdbcTemplate.query(
                    sql,
                    new Object[]{criterio, EstadoEnum.ACTIVO.getValue()},
                    new BeanPropertyRowMapper<>(DominioDTO.class)
            );
        } catch (Exception e) {
            log.error("obtenerDominiosPorDominio(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }

    @Override
    public String obtenerNombrePorDominioPorCodigo(String dominio, String codigo) {
        try {
            String sql = "select nombre from dominio where dominio = ? and codigo = ? and estado = ?;";
            return velacussJdbcTemplate.queryForObject(
                    sql,
                    new Object[]{dominio, codigo, EstadoEnum.ACTIVO.getValue()},
                    new BeanPropertyRowMapper<>(String.class)
            );
        } catch (EmptyResultDataAccessException e) {
            log.info("obtenerNombrePorDominioPorCodigo(...), Empty result: ".concat(Objects.requireNonNull(e.getMessage())));
            return null;
        } catch (Exception e) {
            log.error("obtenerNombrePorDominioPorCodigo(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }

    @Override
    public List<Dominio> buscaTodosDominiosSegunLista(String listaDominios) {
        try {
            String sql = "select * from dominio where dominio in (" + listaDominios + ");";
            return velacussJdbcTemplate.query(
                    sql,
                    new BeanPropertyRowMapper<>(Dominio.class)
            );
        } catch (Exception e) {
            log.error("buscaTodosDominiosSegunLista(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }
}