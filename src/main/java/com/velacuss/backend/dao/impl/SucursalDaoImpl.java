package com.velacuss.backend.dao.impl;

import com.velacuss.backend.dao.SucursalDao;
import com.velacuss.backend.domain.Sucursal;
import com.velacuss.backend.domain.dto.SucursalDTO;
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
public class SucursalDaoImpl implements SucursalDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("velacussJdbcTemplate")
    private JdbcTemplate velacussJdbcTemplate;

    @Override
    public Sucursal obtenerSucursalPorId(Long id) {
        try {
            String sql = "select * from sucursal where id = ? and estado = ?;";
            return velacussJdbcTemplate.queryForObject(
                    sql,
                    new Object[]{id, EstadoEnum.ACTIVO.getValue()},
                    new BeanPropertyRowMapper<>(Sucursal.class)
            );
        } catch (EmptyResultDataAccessException e) {
            log.info("obtenerSucursalPorId(...), Empty result: ".concat(Objects.requireNonNull(e.getMessage())));
            return null;
        } catch (Exception e) {
            log.error("obtenerSucursalPorId(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }

    @Override
    public Boolean exiteSurcusal(String nombre, String numero) {
        try {
            String sql = "SELECT COUNT(*) FROM sucursal WHERE nombre = ? AND numero = ? AND estado = ?";

            int count = velacussJdbcTemplate.queryForObject(sql,
                    new Object[] { nombre, numero, EstadoEnum.ACTIVO.getValue() }, Integer.class);
            if (count >= 1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("exiteSurcusal(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }

    @Override
    public Sucursal crudSucursal(Sucursal sucursal, Integer operacion) {
        try {
            String sql = "select * from crud_sucursal(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            return velacussJdbcTemplate.queryForObject(
              sql,
              new Object[]{
                      sucursal.getId(),
                      sucursal.getLogtransId(),
                      sucursal.getTipo(),
                      sucursal.getNumero(),
                      sucursal.getNombre(),
                      sucursal.getDireccion(),
                      sucursal.getTelefono(),
                      sucursal.getCelular(),
                      sucursal.getFax(),
                      sucursal.getCiudad(),
                      sucursal.getDepartamento(),
                      sucursal.getPais(),
                      operacion
              },
              new BeanPropertyRowMapper<>(Sucursal.class)
            );
        } catch (EmptyResultDataAccessException dataAccessException) {
            log.error("crudSucursal(...), EmptyResult, mensaje del error: ".concat(Objects.requireNonNull(dataAccessException.getMessage())));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        } catch (Exception e) {
            log.error("crudSucursal(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }

    @Override
    public List<Sucursal> listarSucursales() {
        try {
            String sql = "select * from sucursal where estado = ? order by 1 asc";
            return velacussJdbcTemplate.query(
                    sql,
                    new Object[]{EstadoEnum.ACTIVO.getValue()},
                    new BeanPropertyRowMapper<>(Sucursal.class)
            );
        } catch (Exception e) {
            log.error("listarSucursales(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }

    @Override
    public List<SucursalDTO> reporteSucursales() {
        try {
            String sql = "select " +
                    "    d3.nombre as tipo, " +
                    "    s.numero, " +
                    "    s.nombre, " +
                    "    s.direccion, " +
                    "    s.telefono, " +
                    "    s.celular, " +
                    "    s.fax, " +
                    "    s.ciudad, " +
                    "    d1.nombre as departamento, " +
                    "    d2.nombre as pais " +
                    "from sucursal s " +
                    "left join dominio d1 on s.departamento = d1.codigo and d1.dominio = 'departamento' and d1.estado = 'AC' " +
                    "left join dominio d2 on s.pais = d2.codigo and d2.dominio = 'pais' and d2.estado = 'AC' " +
                    "left join dominio d3 on s.tipo = d3.codigo and d3.dominio = 'tipo_sucursal' and d3.estado = 'AC' " +
                    "where s.estado = 'AC' " +
                    "order by s.id asc;";
            return velacussJdbcTemplate.query(
                    sql,
                    new BeanPropertyRowMapper<>(SucursalDTO.class)
            );
        } catch (Exception e) {
            log.error("reporteSucursales(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado!,  contacte con el área de sistemas.");
        }
    }
}
