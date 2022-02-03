package com.velacuss.backend.dao;

import com.velacuss.backend.domain.Sucursal;
import com.velacuss.backend.domain.dto.SucursalDTO;

import java.util.List;

public interface SucursalDao {
    Sucursal obtenerSucursalPorId(Long id);
    Boolean exiteSurcusal(String nombre, String numero);
    Sucursal crudSucursal(Sucursal sucursal, Integer operacion);
    List<Sucursal> listarSucursales();
    List<SucursalDTO> reporteSucursales();
}
