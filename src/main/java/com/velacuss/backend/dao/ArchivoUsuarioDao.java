package com.velacuss.backend.dao;

import java.util.List;

import com.velacuss.backend.domain.ArchivoUsuario;

public interface ArchivoUsuarioDao {
    ArchivoUsuario obtenerArchivoPorId(Long Id);
    ArchivoUsuario crudArchivo(ArchivoUsuario archivo, Integer operacion);
    ArchivoUsuario obtenerArchivoPorUsuario(Long usuarioId);
    List<ArchivoUsuario> listar();
}
