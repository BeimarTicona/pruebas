package com.velacuss.backend.dao.impl;

import com.velacuss.backend.dao.FuncionarioDao;
import com.velacuss.backend.domain.Funcionario;
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
public class FuncionarioDaoImpl implements FuncionarioDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("velacussJdbcTemplate")
    JdbcTemplate velacussJdbcTemplate;

    @Override
    public Funcionario obtenerFuncionarioPorId(Long funcionarioId) {
        try {
            String sql = "SELECT * FROM funcionario WHERE id = ? AND estado = 'AC'; ";
            return velacussJdbcTemplate.queryForObject(
                    sql,
                    new Object[]{funcionarioId},
                    new BeanPropertyRowMapper<>(Funcionario.class)
            );
        } catch (EmptyResultDataAccessException e) {
            log.info("obtenerFuncionarioPorId(...), Empty result: ".concat(Objects.requireNonNull(e.getMessage())));
            return null;
        } catch (Exception e) {
            log.error("obtenerFuncionarioPorId(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }

    @Override
    public Funcionario obtenerFuncionarioPorUsuario(String login) {
        try {
            String sql = "select f.* " +
                    "from usuario u " +
                    "join funcionario f on u.funcionario_id = f.id and u.login = ? and u.estado = 'AC' and f.estado = 'AC';";
            return velacussJdbcTemplate.queryForObject(
                    sql,
                    new Object[]{login},
                    new BeanPropertyRowMapper<>(Funcionario.class)
            );
        } catch (EmptyResultDataAccessException e) {
            log.info("obtenerFuncionarioPorUsuario(...), Empty result: ".concat(Objects.requireNonNull(e.getMessage())));
            return null;
        } catch (Exception e) {
            log.error("obtenerFuncionarioPorUsuario(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }

    @Override
    public Funcionario crudFuncionario(Funcionario funcionario, Integer operacion) {
        try {
            String sql = "select * from crud_funcionario(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            return velacussJdbcTemplate.queryForObject(
                    sql,
                    new Object[]{
                            funcionario.getId(),
                            funcionario.getLogtransId(),
                            funcionario.getNombre(),
                            funcionario.getPrimerApellido(),
                            funcionario.getSegundoApellido(),
                            funcionario.getGenero(),
                            funcionario.getDocIdentidad(),
                            funcionario.getDocTipo(),
                            funcionario.getDocExp(),
                            funcionario.getCargo(),
                            funcionario.getFechaNac(),
                            funcionario.getCorreo(),
                            funcionario.getTelefono(),
                            funcionario.getCelular(),
                            funcionario.getDireccion(),
                            funcionario.getFechaIngreso(),
                            operacion
                    },
                    new BeanPropertyRowMapper<>(Funcionario.class)
            );
        } catch (EmptyResultDataAccessException dataAccessException) {
            log.error("crudFuncionario(...), EmptyResult, mensaje del error: ".concat(Objects.requireNonNull(dataAccessException.getMessage())));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        } catch (Exception e) {
            log.error("crudFuncionario(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }

    @Override
    public List<Funcionario> listarFuncionario() {
        try {
            String sql = "SELECT * FROM funcionario WHERE  estado = 'AC'; ";
            return velacussJdbcTemplate.query(
                    sql,
                    new BeanPropertyRowMapper<>(Funcionario.class)
            );
        } catch (Exception e) {
            log.error("listarFuncionario(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }

    @Override
    public List<Funcionario> listarFuncionariosVendedores() {
        try {
            String sql = "select fu.id, " +
                    "       fu.logtrans_id, " +
                    "       concat_ws(' ', nombre, primer_apellido, segundo_apellido ) as nombre, " +
                    "       fu.primer_apellido, " +
                    "       fu.segundo_apellido, " +
                    "       fu.genero, " +
                    "       fu.doc_identidad, " +
                    "       fu.doc_tipo, " +
                    "       fu.doc_exp, " +
                    "       fu.cargo, " +
                    "       fu.fecha_nac, " +
                    "       fu.correo, " +
                    "       fu.telefono, " +
                    "       fu.celular, " +
                    "       fu.direccion, " +
                    "       fu.fecha_ingreso, " +
                    "       fu.estado, " +
                    "       fu.ant_id " +
                    "from funcionario fu " +
                    "         inner join usuario u on fu.id = u.funcionario_id and u.estado = 'AC' " +
                    "         inner join usuario_rol ur on u.id = ur.usuario_id and ur.rol_id = 6 and ur.estado = 'AC' " +
                    "where fu.estado = 'AC' " +
                    "order by nombre asc; ";
            return velacussJdbcTemplate.query(
                    sql,
                    new BeanPropertyRowMapper<>(Funcionario.class)
            );
        } catch (Exception e) {
            log.error("listarFuncionariosVendedores(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }

    @Override
    public String obtenerNombreCompletoFuncionario(Long funcionarioId) {
        try {
            String sql = "select concat_ws(' ', nombre, primer_apellido, segundo_apellido ) as nombre_funcionario from funcionario where id = ?;";
            return velacussJdbcTemplate.queryForObject(sql, new Object[] { funcionarioId }, String.class);
        } catch (EmptyResultDataAccessException e) {
            log.info("obtenerNombreCompletoFuncionario(...), Empty result: ".concat(Objects.requireNonNull(e.getMessage())));
            return "";
        } catch (Exception e) {
            log.error("obtenerNombreCompletoFuncionario(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }

    @Override
    public String obtenerNombreCompletoUsuario(Long usuarioId) {
        try {
            String sql = "select concat_ws(' ', nombre, primer_apellido, segundo_apellido ) as nombre_funcionario " +
                    "from funcionario f " +
                    "join usuario u on u.funcionario_id = f.id and u.id = ?;";
            return velacussJdbcTemplate.queryForObject(sql, new Object[] { usuarioId }, String.class);
        } catch (EmptyResultDataAccessException e) {
            log.info("obtenerNombreCompletoUsuario(...), Empty result: ".concat(Objects.requireNonNull(e.getMessage())));
            return "";
        } catch (Exception e) {
            log.error("obtenerNombreCompletoUsuario(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }
}
