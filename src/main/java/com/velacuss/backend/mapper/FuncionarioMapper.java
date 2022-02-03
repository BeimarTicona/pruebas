package com.velacuss.backend.mapper;

import com.velacuss.backend.domain.Funcionario;
import com.velacuss.backend.domain.dto.FuncionarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface FuncionarioMapper {
    Funcionario toEntity(FuncionarioDTO dto);
    FuncionarioDTO toDto(Funcionario entity);
    List<Funcionario> toEntity(List<FuncionarioDTO> dtoList);
    List<FuncionarioDTO> toDto(List<Funcionario> entityList);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "logtransId", ignore = true)
    @Mapping(target = "estado", ignore = true)
    void updateToEntity(FuncionarioDTO funcionarioDTO, @MappingTarget Funcionario funcionario);
}
