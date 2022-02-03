package com.velacuss.backend.service.impl;

import com.velacuss.backend.dao.*;
import com.velacuss.backend.domain.Funcionario;
import com.velacuss.backend.domain.Sucursal;
import com.velacuss.backend.domain.dto.SucursalDTO;
import com.velacuss.backend.domain.dto.SucursalInicioDTO;
import com.velacuss.backend.enums.DominioEnum;
import com.velacuss.backend.exceptions.BadRequestException;
import com.velacuss.backend.exceptions.ConflictException;
import com.velacuss.backend.exceptions.NotFoundException;
import com.velacuss.backend.library.LibraryService;
import com.velacuss.backend.mapper.SucursalMapper;
import com.velacuss.backend.service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Transactional
@Service
public class SucursalServiceImpl implements SucursalService {

    @Autowired
    private SucursalDao sucursalDao;
    @Autowired
    private SucursalMapper sucursalMapper;
    @Autowired
    private DominioDao dominioDao;
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private FuncionarioDao funcionarioDao;
    @Autowired
    private LibraryService libraryService;


    @Override
    public SucursalInicioDTO inicio() {
        SucursalInicioDTO inicioDTO = new SucursalInicioDTO();
        inicioDTO.setPaises(dominioDao.obtenerDominiosPorDominio(DominioEnum.PAIS.getValue()));
        inicioDTO.setDepartamentos(dominioDao.obtenerDominiosPorDominio(DominioEnum.DEPARTAMENTO.getValue()));
        inicioDTO.setTiposSucursal(dominioDao.obtenerDominiosPorDominio(DominioEnum.TIPO_SUCURSAL.getValue()));
        return inicioDTO;
    }

    @Override
    public List<SucursalDTO> listar() {
        return sucursalMapper.toDto(sucursalDao.listarSucursales());
    }


    @Override
    public SucursalDTO guardar(SucursalDTO sucursalDTO) {
        if(sucursalDTO==null)
            throw new BadRequestException("La solicitud debe tener una sucursal");
        if (sucursalDao.exiteSurcusal(sucursalDTO.getNombre(), sucursalDTO.getNumero()))
            throw new ConflictException("La sucursal ya esta registrada");
        Sucursal sucursal = sucursalMapper.toEntity(sucursalDTO);
        sucursal.setLogtransId(1L);
        sucursal = sucursalDao.crudSucursal(sucursal, 1);
        return sucursalMapper.toDto(sucursal);
    }

    @Override
    public SucursalDTO obtener(Long id) {
        Sucursal sucursal = sucursalDao.obtenerSucursalPorId(id);
        if (sucursal == null)
            throw new NotFoundException("Sucursal no encontrada");
        return sucursalMapper.toDto(sucursal);
    }

    @Override
    public SucursalDTO editar(SucursalDTO sucursalDTO) {
        Sucursal sucursal = sucursalDao.obtenerSucursalPorId(sucursalDTO.getId());
        if (sucursal == null)
            throw new NotFoundException("Sucursal no encontrada");
        if(sucursal.equals(sucursalMapper.toEntity(sucursalDTO)))
            throw new ConflictException("La sucursal no presenta cambios");
        if(!Objects.equals(sucursal.getNombre(), sucursalDTO.getNombre()) && !Objects.equals(sucursal.getNumero(), sucursalDTO.getNumero())) {
            if (sucursalDao.exiteSurcusal(sucursalDTO.getNombre(), sucursalDTO.getNumero()))
                throw new ConflictException("La sucursal ya esta registrada");
        }
        sucursalMapper.updateToEntity(sucursalDTO, sucursal);
        sucursal = sucursalDao.crudSucursal(sucursal, 2);
        return sucursalMapper.toDto(sucursal);
    }

    @Override
    public void eliminar(Long id) {
        Sucursal sucursal = sucursalDao.obtenerSucursalPorId(id);
        if (sucursal == null)
            throw new NotFoundException("Sucursal no encontrada");
        if(usuarioDao.existeUsuariosPorSurcusal(sucursal.getId()))
            throw new ConflictException("La sucursal no se puede eliminar, ya tiene usuarios registrados.");
        sucursalDao.crudSucursal(sucursal, 3);
    }

    @Override
    public byte[] reporte(String login) {
        Funcionario funcionario = funcionarioDao.obtenerFuncionarioPorUsuario(login);
        if(funcionario==null)
            throw new BadCredentialsException("No tiene autorización");
        List<SucursalDTO> listaSucursal = sucursalDao.reporteSucursales();
        if(listaSucursal.size()==0)
            throw new NotFoundException("No se encontraron lineas registradas");

        ClassLoader classLoader = getClass().getClassLoader();
        Map<String, Object> parametros = new HashMap<>();

        String logoVelacuss = classLoader.getResource("reporte/images/logo-on-dark.png").getPath();
        parametros.put("logo", logoVelacuss);

        parametros.put("empresa", "Velacuss");
        parametros.put("direccion", "Av A");
        parametros.put("gestion", LocalDateTime.now().getYear());
        parametros.put("fecha", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        parametros.put("usuario", libraryService.devuelveNombre(funcionario.getNombre(), funcionario.getPrimerApellido(), funcionario.getSegundoApellido()));

        return this.libraryService.exportarReportePdfJasper("/reporte/sucursal.jasper", parametros, listaSucursal);
    }

    @Override
    public Long obtenerIdSucursalCentral(){
        //Modificar este bloque de codigo para determinar el id de la oficina central.
        return 4L;
    }

    @Override
    public Long obtenerIdAlmacenCentral(){
        //Modificar este bloque de codigo para determinar el id del almacen central.
        return 1L;
    }
}
