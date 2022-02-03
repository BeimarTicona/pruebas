package com.velacuss.backend.mapper;

import com.velacuss.backend.domain.Usuario;
import com.velacuss.backend.domain.dto.UsuarioDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface UsuarioMapper {
    Usuario toEntity(UsuarioDTO dto);

    UsuarioDTO toDto(Usuario entity);

    List<Usuario> toEntity(List<UsuarioDTO> dtoList);

    List<UsuarioDTO> toDto(List<Usuario> entityList);
}
