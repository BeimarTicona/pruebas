package com.velacuss.backend.controller;

import com.velacuss.backend.domain.dto.LineaDTO;
import com.velacuss.backend.service.LineaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class LineaRestController {
    @Autowired
    private LineaService lineaService;

    @GetMapping("/linea/listar")
    public List<LineaDTO> lista() {
        return lineaService.listar();
    }

    @PostMapping("/linea")
    public ResponseEntity<?> guardar(@RequestBody LineaDTO lineaDTO) {
        Map<String, Object> response = new HashMap<>();
        LineaDTO resultado = lineaService.guardar(lineaDTO);
        if(resultado==null) {
            response.put("mensaje", "No se guardo la linea!");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
        }
        response.put("mensaje", "La linea ha sido creada con éxito!");
        response.put("response", resultado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @GetMapping("/linea/{id}")
    public ResponseEntity<?> obtener(@PathVariable("id") Long id) {
        LineaDTO lineaDTO= lineaService.obtener(id);
        return new ResponseEntity<LineaDTO>(lineaDTO, HttpStatus.OK);
    }

    @PutMapping("/linea")
    public ResponseEntity<?> editar(@RequestBody LineaDTO lineaDTO) {
        Map<String, Object> response = new HashMap<>();
        LineaDTO resultado = lineaService.editar(lineaDTO);
        if(resultado==null) {
            response.put("mensaje", "No se guardo la linea!");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
        }
        response.put("mensaje", "La linea ha sido actualizada con éxito!");
        response.put("response", resultado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @DeleteMapping("/linea/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        lineaService.eliminar(id);
        response.put("mensaje", "La linea se ha sido eliminada con éxito!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping("/linea/pdf")
    public ResponseEntity<?> reportePDF(Authentication authentication) {
        Map<String, Object> response = new HashMap<>();
        byte[] bytes = lineaService.reportePDF(authentication.getName());
        if(bytes == null){
            response.put("mensaje", "Reporte no generado!");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
        }
        ContentDisposition contentDisposition = ContentDisposition.builder("inline").filename("linea.pdf").build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity
                .ok()
                .header("Content-Type", "application/pdf; charset=UTF-8")
                .headers(headers)
                .body(bytes);
    }

    @GetMapping("/linea/excel")
    public ResponseEntity<?> reporteEXCEL(Authentication authentication) {
        Map<String, Object> response = new HashMap<>();
        byte[] bytes = lineaService.reporteEXCEL(authentication.getName());
        if(bytes == null){
            response.put("mensaje", "Reporte no generado!");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
        }
        ContentDisposition contentDisposition = ContentDisposition.builder("inline").filename("reporte.xlsx").build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity
                .ok()
                .header("Content-Type", "application/vnd.ms-excel")
                .headers(headers)
                .body(bytes);
    }
}
