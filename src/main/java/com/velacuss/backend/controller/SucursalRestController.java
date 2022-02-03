package com.velacuss.backend.controller;

import com.velacuss.backend.domain.dto.SucursalDTO;
import com.velacuss.backend.domain.dto.SucursalInicioDTO;
import com.velacuss.backend.service.SucursalService;
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
public class SucursalRestController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping("/sucursal/inicio")
    public SucursalInicioDTO inicio(){
        return sucursalService.inicio();
    }

    @GetMapping("/sucursal/listar")
    public List<SucursalDTO> lista(){
        return sucursalService.listar();
    }

    @PostMapping("/sucursal")
    public ResponseEntity<?> guardar(@RequestBody SucursalDTO sucursalDTO){
        Map<String, Object> response = new HashMap<>();
        SucursalDTO resultado = sucursalService.guardar(sucursalDTO);
        if(resultado==null) {
            response.put("mensaje", "No se guardo la sucursal!");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
        }
        response.put("mensaje", "La sucursal ha sido creada con éxito!");
        response.put("response", resultado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @GetMapping("/sucursal/{id}")
    public ResponseEntity<?> obtener(@PathVariable("id") Long id){
        SucursalDTO sucursalDTO= sucursalService.obtener(id);
        return new ResponseEntity<SucursalDTO>(sucursalDTO, HttpStatus.OK);
    }

    @PutMapping("/sucursal")
    public ResponseEntity<?> editar(@RequestBody SucursalDTO sucursalDTO){
        Map<String, Object> response = new HashMap<>();
        SucursalDTO resultado = sucursalService.editar(sucursalDTO);
        if(resultado==null) {
            response.put("mensaje", "No se guardo la sucursal!");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
        }
        response.put("mensaje", "La sucursal ha sido actualizada con éxito!");
        response.put("response", resultado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @DeleteMapping("/sucursal/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") Long id){
        Map<String, Object> response = new HashMap<>();
        sucursalService.eliminar(id);
        response.put("mensaje", "La sucursal se ha sido eliminada con éxito!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping("/sucursal/pdf")
    public ResponseEntity<?> reporte(Authentication authentication) {
        Map<String, Object> response = new HashMap<>();
        byte[] bytes = sucursalService.reporte(authentication.getName());
        if(bytes == null){
            response.put("mensaje", "Reporte no generado!");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
        }
        ContentDisposition contentDisposition = ContentDisposition.builder("inline").filename("sucursal.pdf").build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity
                .ok()
                .header("Content-Type", "application/pdf; charset=UTF-8")
                .headers(headers)
                .body(bytes);
    }
}
