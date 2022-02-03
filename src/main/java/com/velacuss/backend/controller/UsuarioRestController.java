package com.velacuss.backend.controller;

import com.velacuss.backend.domain.dto.UsuarioDTO;
import com.velacuss.backend.domain.dto.UsuarioInicioDTO;
import com.velacuss.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class UsuarioRestController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuario/inicio")
    public UsuarioInicioDTO inicio(){
        return usuarioService.inicio();
    }

    @PostMapping("/usuario")
    public ResponseEntity<?> guardar(@RequestBody UsuarioDTO usuarioDTO) {
        Map<String, Object> response = new HashMap<>();
        UsuarioDTO resultado = usuarioService.guardar(usuarioDTO);
        if(resultado==null) {
            response.put("mensaje", "No se guardo el usuario!");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<UsuarioDTO>(resultado, HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> obtener(@PathVariable("id") Long id) {
        UsuarioDTO usuarioDTO= usuarioService.obtener(id);
        return new ResponseEntity<UsuarioDTO>(usuarioDTO, HttpStatus.OK);
    }

    @PutMapping("/usuario")
    public ResponseEntity<?> editar(@RequestBody UsuarioDTO usuarioDTO) {
        Map<String, Object> response = new HashMap<>();
        UsuarioDTO resultado = usuarioService.editar(usuarioDTO);
        if(resultado==null) {
            response.put("mensaje", "No se edito el usuario!");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<UsuarioDTO>(resultado, HttpStatus.OK);
    }

    @PutMapping("/usuario/rol")
    public ResponseEntity<?> actualizarRol(@RequestBody UsuarioDTO usuarioDTO) {
        Map<String, Object> response = new HashMap<>();
        UsuarioDTO resultado = usuarioService.actualizarRol(usuarioDTO);
        if(resultado==null) {
            response.put("mensaje", "No se actualizo el rol!");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<UsuarioDTO>(resultado, HttpStatus.OK);
    }

    @PutMapping("/usuario/sucursal")
    public ResponseEntity<?> actualizarSucursal(@RequestBody UsuarioDTO usuarioDTO) {
        Map<String, Object> response = new HashMap<>();
        UsuarioDTO resultado = usuarioService.actualizarSucursal(usuarioDTO);
        if(resultado==null) {
            response.put("mensaje", "No se actualizo la sucursal!");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<UsuarioDTO>(resultado, HttpStatus.OK);
    }

    @PutMapping("/usuario/estado")
    public ResponseEntity<?> actualizarEstado(@RequestBody UsuarioDTO usuarioDTO) {
        Map<String, Object> response = new HashMap<>();
        UsuarioDTO resultado = usuarioService.actualizarEstado(usuarioDTO);
        if(resultado==null) {
            response.put("mensaje", "No se actualizo el estado!");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<UsuarioDTO>(resultado, HttpStatus.OK);
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        usuarioService.eliminar(id);
        response.put("mensaje", "EL usuario se ha sido eliminado con éxito!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping("/usuario/listar")
    public List<UsuarioDTO> listar() {
        return usuarioService.listarUsuarios();
    }
}
