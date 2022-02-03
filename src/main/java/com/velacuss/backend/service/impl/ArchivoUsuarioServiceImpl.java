package com.velacuss.backend.service.impl;

import com.velacuss.backend.dao.ArchivoUsuarioDao;
import com.velacuss.backend.domain.ArchivoUsuario;
import com.velacuss.backend.domain.dto.ArchivoUsuarioDTO;
import com.velacuss.backend.exceptions.BadRequestException;
import com.velacuss.backend.exceptions.InternalServerErrorException;
import com.velacuss.backend.exceptions.NotFoundException;
import com.velacuss.backend.mapper.ArchivoUsuarioMapper;
import com.velacuss.backend.service.ArchivoUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
public class ArchivoUsuarioServiceImpl implements ArchivoUsuarioService {
    private final Logger log = LoggerFactory.getLogger(ArchivoUsuarioServiceImpl.class);

    private final static String UPLOADS_FOLDER = Paths.get(System.getProperty("user.home")
            .concat(File.separator)
            .concat("uploads"))
            .toAbsolutePath()
            .toString();
    private final static String FOLDER = UPLOADS_FOLDER.concat(File.separator).concat("folder_usuario");

    @Autowired
    private ArchivoUsuarioDao archivoDao;
    @Autowired
    private ArchivoUsuarioMapper archivoMapper;

    @Override
    public ArchivoUsuarioDTO obtener(Long usuarioId) {
        return archivoMapper.toDto(archivoDao.obtenerArchivoPorUsuario(usuarioId));
    }
    @Override
    public ArchivoUsuarioDTO guardar(Long usuarioId, MultipartFile multipartFile) {
        if(multipartFile==null)
            throw new BadRequestException("La solicitud debe tener un archivo");
        if (!Objects.equals(multipartFile.getContentType(),"image/jpeg"))
            throw new BadRequestException("El tipo de archivo no es valido");
        InputStream archivo;
        try {
            archivo = multipartFile.getInputStream();
        } catch (IOException e) {
            log.error("editar(...), EmptyResult, mensaje del error: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema al guardar el archivo!,  contacte con el área de sistemas.");
        }
        String nombreArchivo = this.guardarArchivo(usuarioId, archivo);
        ArchivoUsuario archivoNuevo = new ArchivoUsuario();
        archivoNuevo.setUsuarioId(usuarioId);
        archivoNuevo.setNombre(multipartFile.getOriginalFilename().replace(" ", ""));
        archivoNuevo.setUrl(nombreArchivo);
        archivoNuevo.setMimetype(multipartFile.getContentType());
        archivoNuevo.setTamanio((int) multipartFile.getSize());
        return archivoMapper.toDto(archivoDao.crudArchivo(archivoNuevo, 1));
    }
    @Override
    public ArchivoUsuarioDTO editar(Long id, MultipartFile multipartFile) {
        if(multipartFile==null)
            throw new BadRequestException("La solicitud debe tener un archivo");
        if (!Objects.equals(multipartFile.getContentType(),"image/jpeg"))
            throw new BadRequestException("El tipo de archivo no es valido");
        InputStream archivo;
        try {
            archivo = multipartFile.getInputStream();
        } catch (IOException e) {
            log.error("editar(...), EmptyResult, mensaje del error: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema al guardar el archivo!,  contacte con el área de sistemas.");
        }
        ArchivoUsuario archivoAntiguo = archivoDao.obtenerArchivoPorId(id);
        if (archivoAntiguo == null)
            throw new NotFoundException("Archivo no encontrado");
        String nombreArchivo = this.guardarArchivo(archivoAntiguo.getUsuarioId(), archivo);
        ArchivoUsuario archivoNuevo = new ArchivoUsuario();
        archivoNuevo.setUsuarioId(archivoAntiguo.getUsuarioId());
        archivoNuevo.setNombre(multipartFile.getOriginalFilename().replace(" ", ""));
        archivoNuevo.setUrl(nombreArchivo);
        archivoNuevo.setMimetype(multipartFile.getContentType());
        archivoNuevo.setTamanio((int) multipartFile.getSize());
        archivoDao.crudArchivo(archivoAntiguo, 3);
        return archivoMapper.toDto(archivoDao.crudArchivo(archivoNuevo, 1));
    }
    @Override
    public Resource ver(String nombre) {
        try {
            return new UrlResource(getPath(nombre).toUri());
        } catch (MalformedURLException e) {
            log.error("ver(...), ¡Problema inesperado!: ".concat(e.getMessage()));
            throw new NotFoundException("¡Problema inesperado!,  no se encontro el archivo.");
        }
    }
    @Override
    public void eliminar(Long id) {
        ArchivoUsuario archivo = archivoDao.obtenerArchivoPorId(id);
        if (archivo == null)
            throw new NotFoundException("Archivo no encontrado");
        archivoDao.crudArchivo(archivo, 3);
    }

    
    @Override
    public List<ArchivoUsuarioDTO> listar() {
        return archivoMapper.toDto(archivoDao.listar());
    }

    private String guardarArchivo(Long usuarioId, InputStream archivo) {
        try {
            String nombreArchivo = usuarioId +"_"+ UUID.randomUUID();
            Path rutaArchivo = getPath(nombreArchivo);
            Resource recurso = new UrlResource(rutaArchivo.toUri());
            if(!recurso.exists())
                Files.copy(archivo, rutaArchivo);
            return nombreArchivo;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            log.error("guardarArchivo(...), EmptyResult, mensaje del error: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema inesperado ruta no encontrada!,  contacte con el área de sistemas.");
        } catch (IOException e) {
            log.error("guardarArchivo(...), EmptyResult, mensaje del error: ".concat(e.getMessage()));
            throw new InternalServerErrorException("¡Problema al guardar el archivo!,  contacte con el área de sistemas.");
        }
    }
    private Path getPath(String nombre) {
        return  Paths.get(FOLDER).resolve(nombre).toAbsolutePath();
    }
}
