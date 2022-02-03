package com.velacuss.backend.dao.impl;

import com.velacuss.backend.dao.UsuarioDao;
import com.velacuss.backend.domain.Usuario;
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
public class UsuarioDaoImpl implements UsuarioDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("velacussJdbcTemplate")
    private JdbcTemplate velacussJdbcTemplate;

    @Override
    public Usuario obtenerUsuarioPorLogin(String login) {
        try {
            String sql = "SELECT * FROM usuario WHERE login = ? and enabled = true AND estado = 'AC'";
            return velacussJdbcTemplate.queryForObject(
                    sql,
                    new Object[]{login},
                    new BeanPropertyRowMapper<>(Usuario.class)
            );
        } catch (EmptyResultDataAccessException e) {
            log.info("obtenerUsuarioPorLogin(...), Empty result: ".concat(Objects.requireNonNull(e.getMessage())));
            return null;
        } catch (Exception e) {
            log.error("obtenerUsuarioPorLogin(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }

    @Override
    public Usuario obtenerUsuarioPorFuncionario(Long funcionarioId) {
        try {
            String sql = "SELECT * FROM usuario WHERE funcionario_id = ? AND estado = 'AC'";
            return velacussJdbcTemplate.queryForObject(
                    sql,
                    new Object[]{funcionarioId},
                    new BeanPropertyRowMapper<>(Usuario.class)
            );
        } catch (EmptyResultDataAccessException e) {
            log.info("obtenerUsuarioPorFuncionario(...), Empty result: ".concat(Objects.requireNonNull(e.getMessage())));
            return null;
        } catch (Exception e) {
            log.error("obtenerUsuarioPorFuncionario(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }

    @Override
    public Usuario obtenerUsuarioPorId(Long id) {
        try {
            String sql = "SELECT * FROM usuario WHERE id = ? AND estado = 'AC'";
            return velacussJdbcTemplate.queryForObject(
                    sql,
                    new Object[]{id},
                    new BeanPropertyRowMapper<>(Usuario.class)
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
    public Usuario crudUsuario(Usuario usuario, Integer operacion) {
        try {
            String sql = "select * from crud_usuario(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            return velacussJdbcTemplate.queryForObject(
                    sql,
                    new Object[]{
                            usuario.getId(),
                            usuario.getLogtransId(),
                            usuario.getFuncionarioId(),
                            usuario.getSucursalId(),
                            usuario.getLogin(),
                            usuario.getClave(),
                            usuario.getEnabled(),
                            usuario.getIntentos(),
                            operacion
                    },
                    new BeanPropertyRowMapper<>(Usuario.class)
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
    public List<Usuario> listarUsuario() {
        try {
            String sql = "SELECT * FROM usuario WHERE estado = 'AC' order by login asc;";
            return velacussJdbcTemplate.query(
                    sql,
                    new BeanPropertyRowMapper<>(Usuario.class)
            );
        } catch (Exception e) {
            log.error("listarUsuario(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }

    @Override
    public Boolean existeUsuariosPorSurcusal(Long sucursalId) {
        try {
            String sql = "SELECT COUNT(*) FROM usuario WHERE sucursal_id = ? AND estado = ?";

            int count = velacussJdbcTemplate.queryForObject(sql,
                    new Object[] { sucursalId, EstadoEnum.ACTIVO.getValue() }, Integer.class);
            if (count >= 1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("exiteUsuariosPorSurcusal(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }


    @Override
    public Boolean existeUsuarioPorLogin(String login) {
        try {
            String sql = "SELECT COUNT(*) FROM usuario WHERE login = ? AND estado = ?";

            int count = velacussJdbcTemplate.queryForObject(sql,
                    new Object[] { login, EstadoEnum.ACTIVO.getValue() }, Integer.class);
            if (count >= 1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("existeUsuarioPorLogin(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }

}
