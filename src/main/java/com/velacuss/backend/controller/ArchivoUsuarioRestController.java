package com.velacuss.backend.controller;

import com.velacuss.backend.domain.dto.ArchivoUsuarioDTO;
import com.velacuss.backend.service.ArchivoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/usuario/archivo")
public class ArchivoUsuarioRestController {
    @Autowired
    private ArchivoUsuarioService archivoService;

    @GetMapping("/{id}")
    public ArchivoUsuarioDTO listar(@PathVariable("id") Long usuarioId){
        return archivoService.obtener(usuarioId);
    }

    @PostMapping(value = "/guardar")
    public ResponseEntity<?> guardar(@RequestParam("id") Long usuarioId, @RequestParam("archivo") MultipartFile archivo) {
        Map<String, Object> response = new HashMap<>();
        ArchivoUsuarioDTO resultado = archivoService.guardar(usuarioId, archivo);
        if(resultado==null) {
            response.put("mensaje", "No se guardo el archivo!");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<ArchivoUsuarioDTO>(resultado, HttpStatus.CREATED);
    }

    @PostMapping(value = "/editar")
    public ResponseEntity<?> editar(@RequestParam("id") Long id, @RequestParam("archivo") MultipartFile archivo) {
        Map<String, Object> response = new HashMap<>();
        ArchivoUsuarioDTO resultado = archivoService.editar(id, archivo);
        if(resultado==null) {
            response.put("mensaje", "No se actualizo el archivo!");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<ArchivoUsuarioDTO>(resultado, HttpStatus.OK);
    }

    @GetMapping(value = "/img/{filename}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> verFile (@PathVariable("filename") String nombre){
        Resource recurso =  archivoService.ver(nombre);
        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment: filename=\""+recurso.getFilename()+"\"");
        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }

    @DeleteMapping(value = "/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        archivoService.eliminar(id);
        response.put("mensaje", "El archivo se ha sido eliminado con éxito!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}
