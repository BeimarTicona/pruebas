package com.velacuss.backend.library;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface LibraryService {
    byte[] exportarReportePdfJasper(String urlJasperFile, Map<String, Object> parametros);
    byte[] exportarReportePdfJasper(String urlJasperFile, Map<String, Object> parametros, List lista);
    byte[] exportarReportePdfJasperSubreport(String path, String nameReport, String nameSubreport,  Map<String, Object> parametros);
    byte[] exportarReportePdfJasperSubreport(String path, String nameReport, String nameSubreport,  Map<String, Object> parametros, List lista);
    byte[] exportarReporteExcelJasper(String urlJasperFile, Map<String, Object> parametros);
    byte[] exportarReporteExcelJasper(String urlJasperFile, Map<String, Object> parametros, List lista);
    String devuelveNombre(String nombre, String primerApellido, String segundoApellido);
    String devuelveContacto(String telefono, String celular, String fax);
    Integer obtenerGestion(LocalDateTime fecha);
    String obtenerFecha(LocalDateTime fecha);
    String obtenerFechaHora(LocalDateTime fecha);
    String obtenerFecha(LocalDate fecha);
}
