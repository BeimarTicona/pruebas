package com.velacuss.backend.mapper;

import com.velacuss.backend.domain.ArchivoUsuario;
import com.velacuss.backend.domain.dto.ArchivoUsuarioDTO;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring", uses = {})
public interface ArchivoUsuarioMapper {
    ArchivoUsuario toEntity(ArchivoUsuarioDTO dto);

    ArchivoUsuarioDTO toDto(ArchivoUsuario entity);

    List<ArchivoUsuario> toEntity(List<ArchivoUsuarioDTO> dtoList);

    List<ArchivoUsuarioDTO> toDto(List<ArchivoUsuario> entityList);
}
