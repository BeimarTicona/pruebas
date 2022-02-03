package com.velacuss.backend.dao;

import com.velacuss.backend.domain.Linea;

import java.util.List;

public interface LineaDao {
    Linea obtenerLineaPorId(Long id);
    Boolean existeLinea(String nombre);
    Linea crudLinea(Linea linea, Integer operacion);
    List<Linea> listarLineas();
}
