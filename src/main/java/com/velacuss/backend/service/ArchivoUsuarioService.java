package com.velacuss.backend.service;

import java.util.List;

import com.velacuss.backend.domain.dto.ArchivoUsuarioDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ArchivoUsuarioService {
    ArchivoUsuarioDTO obtener(Long usuarioId);
    ArchivoUsuarioDTO guardar(Long usuarioId, MultipartFile multipartFile);
    ArchivoUsuarioDTO editar(Long id, MultipartFile multipartFile);
    Resource ver(String nombre);
    void eliminar(Long id);
    List<ArchivoUsuarioDTO> listar();
}
