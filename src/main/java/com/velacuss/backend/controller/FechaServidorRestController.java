package com.velacuss.backend.controller;

import com.velacuss.backend.dao.SistemaFechaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class FechaServidorRestController {

    @Autowired
    private SistemaFechaDao sistemaFechaDao;

    @GetMapping("/fecha")
    public LocalDate obtenerFechaServidor(){
        return sistemaFechaDao.obtenerFechaServidor();
    }

    @GetMapping("/fecha-hora")
    public LocalDateTime obtenerFechaHoraServidor(){
        return sistemaFechaDao.obtenerFechaHoraServidor();
    }
}
