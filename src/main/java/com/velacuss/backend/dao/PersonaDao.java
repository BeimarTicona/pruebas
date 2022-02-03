package com.velacuss.backend.dao;

import com.velacuss.backend.domain.Persona;

import java.util.List;

public interface PersonaDao {
    Persona obtenerPersonaPorId(Long PersonaId);

    Persona obtenerPersonaPorUsuario(Long id);

    Persona crudPersona(Persona persona, Integer operacion);

    List<Persona> listarPersona();

}
