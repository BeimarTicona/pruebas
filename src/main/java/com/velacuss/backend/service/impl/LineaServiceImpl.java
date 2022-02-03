package com.velacuss.backend.service.impl;

import com.velacuss.backend.dao.*;
import com.velacuss.backend.domain.Funcionario;
import com.velacuss.backend.domain.Linea;
import com.velacuss.backend.domain.dto.LineaDTO;
import com.velacuss.backend.enums.ImageEnum;
import com.velacuss.backend.enums.ReporteEnum;
import com.velacuss.backend.exceptions.BadRequestException;
import com.velacuss.backend.exceptions.ConflictException;
import com.velacuss.backend.exceptions.NotFoundException;
import com.velacuss.backend.library.LibraryService;
import com.velacuss.backend.mapper.LineaMapper;
import com.velacuss.backend.service.LineaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Transactional
public class LineaServiceImpl implements LineaService {
    @Autowired
    private LineaDao lineaDao;
    @Autowired
    private LineaMapper lineaMapper;
    @Autowired
    private LibraryService libraryService;
    @Autowired
    private FuncionarioDao funcionarioDao;
    @Autowired
    private SistemaFechaDao sistemaFechaDao;

    @Override
    public List<LineaDTO> listar() {
        return lineaMapper.toDto(lineaDao.listarLineas());
    }

    @Override
    public LineaDTO guardar(LineaDTO lineaDTO) {
        if (lineaDTO == null)
            throw new BadRequestException("La solicitud debe tener una linea");

        if (lineaDao.existeLinea(lineaDTO.getNombre()))
            throw new ConflictException("La linea ya esta registrada");

        Linea linea = lineaMapper.toEntity(lineaDTO);
        linea.setLogtransId(1L);
        linea = lineaDao.crudLinea(linea, 1);
        return lineaMapper.toDto(linea);
    }

    @Override
    public LineaDTO obtener(Long id) {
        Linea linea = lineaDao.obtenerLineaPorId(id);

        if (linea == null)
            throw new NotFoundException("Linea no encontrada");

        return lineaMapper.toDto(linea);
    }

    @Override
    public LineaDTO editar(LineaDTO lineaDTO) {
        if (lineaDTO == null)
            throw new BadRequestException("La solicitud debe tener una linea");

        Linea linea = lineaDao.obtenerLineaPorId(lineaDTO.getId());
        if (linea == null)
            throw new NotFoundException("Linea no encontrada");

        if (linea.equals(lineaMapper.toEntity(lineaDTO)))
            throw new ConflictException("La linea no presenta cambios");

        if (!Objects.equals(linea.getNombre(), lineaDTO.getNombre())) {
            if (lineaDao.existeLinea(lineaDTO.getNombre()))
                throw new ConflictException("La linea ya esta registrada");
        }

        lineaMapper.updateToEntity(lineaDTO, linea);

        linea = lineaDao.crudLinea(linea, 2);
        return lineaMapper.toDto(linea);
    }

    @Override
    public void eliminar(Long id) {
        Linea linea = lineaDao.obtenerLineaPorId(id);
        if (linea == null)
            throw new NotFoundException("Linea no encontrada");

        lineaDao.crudLinea(linea, 3);
    }

    @Override
    public byte[] reportePDF(String login) {

        return this.libraryService.exportarReportePdfJasper(ReporteEnum.REPORTE_LINEA_PDF.getValue(), this.obtenerParametros(login), this.obtenerListado());

    }

    @Override
    public byte[] reporteEXCEL(String login) {
        return this.libraryService.exportarReporteExcelJasper(ReporteEnum.REPORTE_LINE_EXCEL.getValue(), this.obtenerParametros(login), this.obtenerListado());
    }

    private Map<String, Object> obtenerParametros(String login) {

        Funcionario funcionario = funcionarioDao.obtenerFuncionarioPorUsuario(login);
        if (funcionario == null)
            throw new BadCredentialsException("No tiene autorización");

        LocalDateTime fechaServidor = sistemaFechaDao.obtenerFechaHoraServidor();

        ClassLoader classLoader = getClass().getClassLoader();
        Map<String, Object> parametros = new HashMap<>();

        String logoVelacuss = classLoader.getResource("reporte/images/logo-on-dark.png").getPath();
        parametros.put("logo", logoVelacuss);
        parametros.put("empresa", "Velacuss");
        parametros.put("direccion", "Av Avenida");
        parametros.put("gestion", libraryService.obtenerGestion(fechaServidor));
        parametros.put("fecha", libraryService.obtenerFecha(fechaServidor));
        parametros.put("usuario", libraryService.devuelveNombre(funcionario.getNombre(), funcionario.getPrimerApellido(), funcionario.getSegundoApellido()));

        return parametros;
    }

    private List<LineaDTO> obtenerListado() {
        List<LineaDTO> listaLinea = this.listar();

        if (listaLinea.size() == 0)
            throw new NotFoundException("No se encontraron lineas registradas");

        return listaLinea;
    }
}
