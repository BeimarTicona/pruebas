package com.velacuss.backend.library.impl;

import com.velacuss.backend.library.UploadFileService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class UploadFileServiceImpl implements UploadFileService {
    private final static String UPLOADS_FOLDER = Paths.get(System.getProperty("user.home")
            .concat(File.separator)
            .concat("uploads"))
            .toAbsolutePath()
            .toString();

    private final static String FOLDER_PRODUCTO = UPLOADS_FOLDER.concat(File.separator).concat("folder_producto");
    private final static String FOLDER_CLIENTE = UPLOADS_FOLDER.concat(File.separator).concat("folder_cliente");
    private final static String FOLDER_USUARIO = UPLOADS_FOLDER.concat(File.separator).concat("folder_usuario");

    @Override
    public void init() throws IOException {
        File file = new File(UPLOADS_FOLDER);
        if (!file.exists()) {
            Files.createDirectory(Paths.get(UPLOADS_FOLDER));
        }
        File fileFolder = new File(FOLDER_PRODUCTO);
        if (!fileFolder.exists()) {
            Files.createDirectory(Paths.get(FOLDER_PRODUCTO));
        }
        File fileFolderCliente = new File(FOLDER_CLIENTE);
        if (!fileFolderCliente.exists()) {
            Files.createDirectory(Paths.get(FOLDER_CLIENTE));
        }
        File fileFolderUsuario = new File(FOLDER_USUARIO);
        if (!fileFolderUsuario.exists()) {
            Files.createDirectory(Paths.get(FOLDER_USUARIO));
        }
    }
}
