package com.velacuss.backend.mapper;

import com.velacuss.backend.domain.Linea;
import com.velacuss.backend.domain.dto.LineaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface LineaMapper {
    Linea toEntity(LineaDTO dto);
    LineaDTO toDto(Linea entity);
    List<Linea> toEntity(List<LineaDTO> dtoList);
    List<LineaDTO> toDto(List<Linea> entityList);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "logtransId", ignore = true)
    @Mapping(target = "estado", ignore = true)
    void updateToEntity(LineaDTO lineaDTO, @MappingTarget Linea linea);
}
