package com.velacuss.backend.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface SistemaFechaDao {
    LocalDateTime obtenerFechaHoraServidor();
    LocalDate obtenerFechaServidor();
}
