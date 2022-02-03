package com.velacuss.backend.mapper;

import com.velacuss.backend.domain.Rol;
import com.velacuss.backend.domain.dto.RolDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface RolMapper {
    Rol toEntity(RolDTO dto);

    RolDTO toDto(Rol entity);

    List<Rol> toEntity(List<RolDTO> dtoList);

    List<RolDTO> toDto(List<Rol> entityList);
}
