package com.velacuss.backend.controller;

import com.velacuss.backend.domain.dto.PersonaDTO;
import com.velacuss.backend.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PersonaRestController {
    @Autowired
    private PersonaService personaService;

    @PostMapping("/persona")
    public ResponseEntity<?> guardar(@RequestBody PersonaDTO personaDTO) {
        Map<String, Object> response = new HashMap<>();
        PersonaDTO resultado = personaService.guardar(personaDTO);
        if (resultado == null) {
            response.put("mensaje", "No se guardo la persona!");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<PersonaDTO>(resultado, HttpStatus.CREATED);
    }

    @GetMapping("/persona/{id}")
    public ResponseEntity<?> obtener(@PathVariable("id") Long id) {
        PersonaDTO personaDTO = personaService.obtener(id);
        return new ResponseEntity<PersonaDTO>(personaDTO, HttpStatus.OK);
    }

    @PutMapping("/persona")
    public ResponseEntity<?> editar(@RequestBody PersonaDTO personaDTO) {
        Map<String, Object> response = new HashMap<>();
        PersonaDTO resultado = personaService.editar(personaDTO);
        if (resultado == null) {
            response.put("mensaje", "No se edito el usuario!");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<PersonaDTO>(resultado, HttpStatus.OK);
    }

    @DeleteMapping("/persona/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        personaService.eliminar(id);
        response.put("mensaje", "La persona se ha sido eliminado con éxito!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping("/persona/listar")
    public List<PersonaDTO> listar() {
        return personaService.listarPersonas();
    }

}
