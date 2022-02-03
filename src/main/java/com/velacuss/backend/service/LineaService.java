package com.velacuss.backend.service;

import com.velacuss.backend.domain.dto.LineaDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface LineaService {
    List<LineaDTO> listar();
    LineaDTO guardar(LineaDTO lineaDTO);
    LineaDTO obtener(Long id);
    LineaDTO editar(LineaDTO lineaDTO);
    void eliminar(Long id);
    byte[] reportePDF(String login);
    byte[] reporteEXCEL(String login);
}
