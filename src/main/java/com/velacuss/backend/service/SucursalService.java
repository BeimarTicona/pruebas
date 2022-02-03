package com.velacuss.backend.service;

import com.velacuss.backend.domain.dto.SucursalDTO;
import com.velacuss.backend.domain.dto.SucursalInicioDTO;

import java.util.List;

public interface SucursalService {
    SucursalInicioDTO inicio();

    List<SucursalDTO> listar();

    SucursalDTO guardar(SucursalDTO sucursalDTO);

    SucursalDTO obtener(Long id);

    SucursalDTO editar(SucursalDTO sucursalDTO);

    void eliminar(Long id);

    byte[] reporte(String login);

    Long obtenerIdSucursalCentral();

    Long obtenerIdAlmacenCentral();

}
