package com.velacuss.backend.service;

import com.velacuss.backend.domain.dto.PersonaDTO;

import java.util.List;

public interface PersonaService {
    PersonaDTO guardar(PersonaDTO personaDTO);
    PersonaDTO obtener(Long id);
    PersonaDTO editar(PersonaDTO personaDTO);
    void eliminar(Long id);
    List<PersonaDTO> listarPersonas();
}
