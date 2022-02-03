package com.velacuss.backend.mapper;

import com.velacuss.backend.domain.Persona;
import com.velacuss.backend.domain.dto.PersonaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface PersonaMapper {
    Persona toEntity(PersonaDTO dto);
    PersonaDTO toDto(Persona entity);
    List<Persona> toEntity(List<PersonaDTO> dtoList);
    List<PersonaDTO> toDto(List<Persona> entityList);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "logtransId", ignore = true)
    @Mapping(target = "estado", ignore = true)
    void updateToEntity(PersonaDTO personaDTO, @MappingTarget Persona persona);


}
