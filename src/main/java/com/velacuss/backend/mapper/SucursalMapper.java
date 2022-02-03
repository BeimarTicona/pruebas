package com.velacuss.backend.mapper;

import com.velacuss.backend.domain.Sucursal;
import com.velacuss.backend.domain.dto.SucursalDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface SucursalMapper {
    Sucursal toEntity(SucursalDTO dto);
    SucursalDTO toDto(Sucursal entity);
    List<Sucursal> toEntity(List<SucursalDTO> dtoList);
    List<SucursalDTO> toDto(List<Sucursal> entityList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "logtransId", ignore = true)
    @Mapping(target = "estado", ignore = true)
    void updateToEntity(SucursalDTO sucursalDTO, @MappingTarget Sucursal sucursal);
}
