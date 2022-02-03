package com.velacuss.backend.service.impl;

import com.velacuss.backend.dao.PersonaDao;
import com.velacuss.backend.domain.Persona;
import com.velacuss.backend.domain.dto.PersonaDTO;
import com.velacuss.backend.domain.dto.UsuarioDTO;
import com.velacuss.backend.exceptions.BadRequestException;
import com.velacuss.backend.exceptions.NotFoundException;
import com.velacuss.backend.mapper.PersonaMapper;
import com.velacuss.backend.service.PersonaService;
import com.velacuss.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class PersonaServiceImpl implements PersonaService {
    @Autowired
    private PersonaDao personaDao;
    @Autowired
    private PersonaMapper personaMapper;
    @Autowired
    private UsuarioService usuarioService;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public PersonaDTO guardar(PersonaDTO personaDTO) {
        if (personaDTO == null)
            throw new BadRequestException("La solicitud debe tener una persona");

        if (personaDTO.getUsuarioId() == null)
            throw new BadRequestException("La solicitud debe tener un usuario asignado");

        if (personaDTO.getNombre() == null)
            throw new BadRequestException("La solicitud debe tener una nombre asignado");


        Persona persona = personaMapper.toEntity(personaDTO);
        persona.setLogtransId(1L);
        return personaMapper.toDto(personaDao.crudPersona(persona, 1));
    }

    @Override
    public PersonaDTO obtener(Long id) {
        Persona persona = personaDao.obtenerPersonaPorId(id);
        if (persona == null)
            throw new NotFoundException("Persona no encontrada");

        return personaMapper.toDto(persona);
    }

    @Override
    public PersonaDTO editar(PersonaDTO personaDTO) {
        if (personaDTO == null)
            throw new BadRequestException("La solicitud debe tener un dato");
        Persona persona = personaDao.obtenerPersonaPorId(personaDTO.getId());
        if (persona == null)
            throw new NotFoundException("Persona no encontrada");

        personaMapper.updateToEntity(personaDTO, persona);
        return personaMapper.toDto(personaDao.crudPersona(persona, 2));
    }

    @Override
    public void eliminar(Long id) {

        Persona persona = personaDao.obtenerPersonaPorId(id);
        if (persona == null)
            throw new NotFoundException("persona no encontrada");
        personaDao.crudPersona(persona, 3);
    }

    @Override
    public List<PersonaDTO> listarPersonas() {
        List<PersonaDTO> personas = new ArrayList<>();

        List<Persona> person = personaDao.listarPersona();
        if (person.size() > 0) {
            List<UsuarioDTO> usuarios = usuarioService.listarUsuarios();
            for (Persona persona : person) {
                PersonaDTO personaDTO = personaMapper.toDto(persona);
                for (UsuarioDTO us : usuarios) {
                    if (Objects.equals(persona.getUsuarioId(), us.getId())) {
                        personaDTO.setUsuario(us);
                        break;
                    }
                }
                personas.add(personaDTO);
            }

        }

        return personas;
    }
}
