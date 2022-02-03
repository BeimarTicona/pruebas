package com.velacuss.backend.library.impl;

import com.velacuss.backend.exceptions.InternalServerErrorException;
import com.velacuss.backend.library.LibraryService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class LibraryServiceImpl implements LibraryService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public byte[] exportarReportePdfJasper(String urlJasperFile, Map<String, Object> parametros) {
        try {
            InputStream jasperStream1 = this.getClass().getResourceAsStream(urlJasperFile);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream1);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, new JREmptyDataSource());
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            log.error("exportarReportePdfJasper(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado al generar el reporte!,  contacte con el área de sistemas.");
        } catch (Exception e) {
            log.error("exportarReportePdfJasper(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado al generar el reporte!,  contacte con el área de sistemas.");
        }
    }

    @Override
    public byte[] exportarReportePdfJasper(String urlJasperFile, Map<String, Object> parametros, List lista) {
        try {
            InputStream jasperStream1 = this.getClass().getResourceAsStream(urlJasperFile);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream1);
            JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(lista, false);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, source);
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            log.error("exportarReportePdfJasper(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado al generar el reporte!,  contacte con el área de sistemas.");
        } catch (Exception e) {
            log.error("exportarReportePdfJasper(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado al generar el reporte!,  contacte con el área de sistemas.");
        }
    }

    @Override
    public byte[] exportarReportePdfJasperSubreport(String path, String nameReport, String nameSubreport,  Map<String, Object> parametros) {
        try {
            InputStream facturaStream = getClass().getResourceAsStream(path.concat(nameReport.concat(".jrxml")));
            JasperReport jasperReport = JasperCompileManager.compileReport(facturaStream);
            JRSaver.saveObject(jasperReport, nameReport.concat(".jasper"));

            InputStream subReportStream = getClass().getResourceAsStream(path.concat(nameSubreport.concat(".jrxml")));
            JRSaver.saveObject(JasperCompileManager.compileReport(subReportStream), nameSubreport.concat(".jasper"));

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, new JREmptyDataSource());
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            log.error("exportarReportePdfJasperSubreport(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado al generar el reporte!,  contacte con el área de sistemas.");
        } catch (Exception e) {
            log.error("exportarReportePdfJasperSubreport(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado al generar el reporte!,  contacte con el área de sistemas.");
        }
    }
    @Override
    public byte[] exportarReportePdfJasperSubreport(String path, String nameReport, String nameSubreport,  Map<String, Object> parametros, List lista) {
        try {
            InputStream facturaStream = getClass().getResourceAsStream(path.concat(nameReport.concat(".jrxml")));
            JasperReport jasperReport = JasperCompileManager.compileReport(facturaStream);
            JRSaver.saveObject(jasperReport, nameReport.concat(".jasper"));

            InputStream subReportStream = getClass().getResourceAsStream(path.concat(nameSubreport.concat(".jrxml")));
            JRSaver.saveObject(JasperCompileManager.compileReport(subReportStream), nameSubreport.concat(".jasper"));

            JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(lista, false);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, source);
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            log.error("exportarReportePdfJasperSubreport(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado al generar el reporte!,  contacte con el área de sistemas.");
        } catch (Exception e) {
            log.error("exportarReportePdfJasperSubreport(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado al generar el reporte!,  contacte con el área de sistemas.");
        }
    }

    @Override
    public byte[] exportarReporteExcelJasper(String urlJasperFile, Map<String, Object> parametros) {
        try {
            InputStream jasperStream1 = this.getClass().getResourceAsStream(urlJasperFile);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream1);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, new JREmptyDataSource());

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
            exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
            exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
            exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BACKGROUND ,Boolean.FALSE);
            exporter.exportReport();
            return baos.toByteArray();
        } catch (JRException e) {
            log.error("exportarReportePdfJasper(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado al generar el reporte!,  contacte con el área de sistemas.");
        } catch (Exception e) {
            log.error("exportarReportePdfJasper(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado al generar el reporte!,  contacte con el área de sistemas.");
        }
    }

    @Override
    public byte[] exportarReporteExcelJasper(String urlJasperFile, Map<String, Object> parametros, List lista) {
        try {
            InputStream jasperStream1 = this.getClass().getResourceAsStream(urlJasperFile);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream1);
            JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(lista, false);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, source);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
            exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
            exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
            exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BACKGROUND ,Boolean.FALSE);
            exporter.exportReport();
            return baos.toByteArray();
        } catch (JRException e) {
            log.error("exportarReporteExcelJasper(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado al generar el reporte!,  contacte con el área de sistemas.");
        } catch (Exception e) {
            log.error("exportarReporteExcelJasper(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado al generar el reporte!,  contacte con el área de sistemas.");
        }
    }

    @Override
    public String devuelveNombre(String nombre, String primerApellido, String segundoApellido) {
        String nombreCompleto = " ";
        if (nombre != null && !nombre.equals("")) {
            nombreCompleto = nombre;
        }
        if (primerApellido != null && !primerApellido.equals("")) {
            nombreCompleto = nombreCompleto + " " + primerApellido;
        }
        if (segundoApellido != null && !segundoApellido.equals("")) {
            nombreCompleto = nombreCompleto + " " + segundoApellido;
        }
        return nombreCompleto.trim();
    }

    @Override
    public String devuelveContacto(String telefono, String celular, String fax) {
        String contacto = "";
        if (telefono != null && !telefono.equals("")) {
            contacto = telefono;
        }
        if (celular != null && !celular.equals("")) {
            if(contacto.equals("")){
                contacto = contacto + celular;
            } else {
                contacto = contacto + " - " + celular;
            }
        }
        if (fax != null && !fax.equals("")) {
            if(contacto.equals("")){
                contacto = contacto + fax;
            } else {
                contacto = contacto + " - " + fax;
            }
        }
        return contacto.trim();
    }

    @Override
    public String obtenerFecha(LocalDateTime fecha) {
        if(fecha==null)
            return null;
        return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public String obtenerFechaHora(LocalDateTime fecha) {
        if(fecha==null)
            return null;
        return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    @Override
    public String obtenerFecha(LocalDate fecha) {
        if(fecha==null)
            return null;
        return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public Integer obtenerGestion(LocalDateTime fecha) {
        if(fecha==null)
            return null;
        return fecha.getYear();
    }
}

