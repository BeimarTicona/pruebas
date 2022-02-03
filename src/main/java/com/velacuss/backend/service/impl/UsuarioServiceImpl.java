package com.velacuss.backend.service.impl;

import com.velacuss.backend.dao.DominioDao;
import com.velacuss.backend.dao.RolDao;
import com.velacuss.backend.dao.UsuarioDao;
import com.velacuss.backend.dao.UsuarioRolDao;
import com.velacuss.backend.domain.*;
import com.velacuss.backend.domain.Usuario;
import com.velacuss.backend.domain.dto.*;
import com.velacuss.backend.enums.DominioEnum;
import com.velacuss.backend.exceptions.BadRequestException;
import com.velacuss.backend.exceptions.ConflictException;
import com.velacuss.backend.exceptions.NotFoundException;
import com.velacuss.backend.mapper.UsuarioMapper;
import com.velacuss.backend.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private UsuarioRolDao usuarioRolDao;
    @Autowired
    private UsuarioMapper usuarioMapper;
    @Autowired
    private RolDao rolDao;
    @Autowired
    private DominioDao dominioDao;
    @Autowired
    private FuncionarioService funcionarioService;
    @Autowired
    private SucursalService sucursalService;
    @Autowired
    private RolService rolService;
    @Autowired
    private ArchivoUsuarioService archivoUsuarioService;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.obtenerUsuarioPorLogin(username);

        if(usuario==null){
            log.error("Error en el login: no existe el usuario '" + username + "' en el sistema.");
            throw new UsernameNotFoundException("error en el login: no existe el usuario '" + username + "' en el sistema.");
        }

        Rol r = rolDao.obtenerRolPorUsuario(usuario.getId());
        List<Rol> roles = new ArrayList<>();
        roles.add(r);
        List<GrantedAuthority> authorities = roles
                .stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getSigla()))
                .collect(Collectors.toList());

        return new User(usuario.getLogin(), usuario.getClave(), usuario.getEnabled(), true, true, true, authorities);
    }

    @Override
    public UsuarioInicioDTO inicio() {
        UsuarioInicioDTO inicioDTO = new UsuarioInicioDTO();
        inicioDTO.setGeneros(dominioDao.obtenerDominiosPorDominio(DominioEnum.TIPO_GENERO.getValue()));
        inicioDTO.setDocumentos(dominioDao.obtenerDominiosPorDominio(DominioEnum.TIPO_DOCUMENTO.getValue()));
        inicioDTO.setDepartamentos(dominioDao.obtenerDominiosPorDominio(DominioEnum.DEPARTAMENTO.getValue()));
        inicioDTO.setSucursales(sucursalService.listar());
        inicioDTO.setRoles(rolService.listarRoles());
        return inicioDTO;
    }

    @Override
    public UsuarioDTO guardar(UsuarioDTO usuarioDTO) {
        if(usuarioDTO==null)
            throw new BadRequestException("La solicitud debe tener un usuario");

        if(usuarioDTO.getRolId()==null)
            throw new BadRequestException("La solicitud debe tener un rol asignado");

        if(usuarioDTO.getSucursalId()==null)
            throw new BadRequestException("La solicitud debe tener una sucursal asignado");

        if (usuarioDao.existeUsuarioPorLogin(usuarioDTO.getLogin()))
            throw new ConflictException("El nombre de usuario ya esta registrado");

        FuncionarioDTO funcionarioDTO = funcionarioService.guardar(usuarioDTO.getFuncionario());

        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario.setLogtransId(1L);
        usuario.setFuncionarioId(funcionarioDTO.getId());
        usuario.setClave(passwordEncoder.encode(usuario.getClave()));
        usuario.setIntentos(0);
        usuario = usuarioDao.crudUsuario(usuario, 1);

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuarioId(usuario.getId());
        usuarioRol.setRolId(usuarioDTO.getRolId());
        usuarioRolDao.crudUsuarioRol(usuarioRol, 1);

        return obtener(usuario.getId());
    }

    @Override
    public UsuarioDTO obtener(Long id) {
        Usuario usuario = usuarioDao.obtenerUsuarioPorId(id);
        return obtenerUsuario(usuario);
    }

    @Override
    public UsuarioDTO editar(UsuarioDTO usuarioDTO) {
        if(usuarioDTO==null)
            throw new BadRequestException("La solicitud debe tener un usuario");

        if(usuarioDTO.getRolId()==null)
            throw new BadRequestException("La solicitud debe tener un rol asignado");

        if(usuarioDTO.getSucursalId()==null)
            throw new BadRequestException("La solicitud debe tener una sucursal asignado");

        Usuario usuario = usuarioDao.obtenerUsuarioPorId(usuarioDTO.getId());
        if (usuario == null)
            throw new NotFoundException("Usuario no encontrado");

        if(!Objects.equals(usuario.getLogin(), usuarioDTO.getLogin())) {
            if (usuarioDao.existeUsuarioPorLogin(usuarioDTO.getLogin()))
                throw new ConflictException("El nombre de usuario ya esta registrado");
        }

        funcionarioService.editar(usuarioDTO.getFuncionario());

        usuario.setSucursalId(usuarioDTO.getSucursalId());
        usuario.setLogin(usuarioDTO.getLogin());
        if(usuarioDTO.getClave() != null && !Objects.equals(usuarioDTO.getClave().trim(), ""))
            usuario.setClave(passwordEncoder.encode(usuarioDTO.getClave()));

        usuario.setIntentos(0);
        usuario.setEnabled(usuarioDTO.getEnabled());
        usuarioDao.crudUsuario(usuario, 2);

        UsuarioRol usuarioRol = usuarioRolDao.obtenerUsuarioRolPorUsuario(usuario.getId());
        if(!Objects.equals(usuarioRol.getRolId(), usuarioDTO.getRolId())) {
            usuarioRol.setRolId(usuarioDTO.getRolId());
            usuarioRolDao.crudUsuarioRol(usuarioRol, 2);
        }
        return obtener(usuario.getId());
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = usuarioDao.obtenerUsuarioPorId(id);
        if (usuario == null)
            throw new NotFoundException("Usuario no encontrado");

        usuarioDao.crudUsuario(usuario, 3);
        funcionarioService.eliminar(usuario.getFuncionarioId());

        UsuarioRol usuarioRol = usuarioRolDao.obtenerUsuarioRolPorUsuario(usuario.getId());
        if(usuarioRol!=null) {
            usuarioRolDao.crudUsuarioRol(usuarioRol, 3);
        }
    }

    @Override
    public List<UsuarioDTO> listarUsuarios() {
        List<UsuarioDTO> usuarios = new ArrayList<>();

        List<Usuario> users = usuarioDao.listarUsuario();
        if(users.size()>0){
            List<FuncionarioDTO> funcionarios = funcionarioService.listaFuncionarios();
            List<UsuarioRol> usuarioRols = usuarioRolDao.listarRoles();
            List<ArchivoUsuarioDTO> archivoUsuarios = archivoUsuarioService.listar(); 
            for (Usuario user: users) {
                UsuarioDTO usuarioDTO = usuarioMapper.toDto(user);
                usuarioDTO.setClave(null);
                for (FuncionarioDTO func: funcionarios) {
                    if(Objects.equals(user.getFuncionarioId(), func.getId())){
                        usuarioDTO.setFuncionario(func);
                        break;
                    }
                }
                for (UsuarioRol ur: usuarioRols) {
                    if(Objects.equals(user.getId(), ur.getUsuarioId())){
                        usuarioDTO.setRolId(ur.getRolId());
                        break;
                    }
                }
                for (ArchivoUsuarioDTO au: archivoUsuarios) {
                    if(Objects.equals(user.getId(), au.getUsuarioId())){
                        usuarioDTO.setArchivo(au);
                        break;
                    }
                }
                usuarios.add(usuarioDTO);
            }
        }
        return usuarios;
    }

    @Override
    public UsuarioDTO actualizarRol(UsuarioDTO usuarioDTO) {
        if(usuarioDTO==null)
            throw new BadRequestException("La solicitud debe tener un usuario");

        Usuario usuario = usuarioDao.obtenerUsuarioPorId(usuarioDTO.getId());
        if (usuario == null)
            throw new NotFoundException("Usuario no encontrado");

        UsuarioRol usuarioRol = usuarioRolDao.obtenerUsuarioRolPorUsuario(usuario.getId());
        if(!Objects.equals(usuarioRol.getRolId(), usuarioDTO.getRolId())) {
            usuarioRol.setRolId(usuarioDTO.getRolId());
            usuarioRolDao.crudUsuarioRol(usuarioRol, 2);
        }
        return obtener(usuario.getId());
    }

    @Override
    public UsuarioDTO actualizarSucursal(UsuarioDTO usuarioDTO) {
        if(usuarioDTO==null)
            throw new BadRequestException("La solicitud debe tener un usuario");

        Usuario usuario = usuarioDao.obtenerUsuarioPorId(usuarioDTO.getId());
        if (usuario == null)
            throw new NotFoundException("Usuario no encontrado");

        usuario.setSucursalId(usuarioDTO.getSucursalId());
        usuarioDao.crudUsuario(usuario, 2);

        return obtener(usuario.getId());
    }

    @Override
    public UsuarioDTO actualizarEstado(UsuarioDTO usuarioDTO) {
        if(usuarioDTO==null)
            throw new BadRequestException("La solicitud debe tener un usuario");

        Usuario usuario = usuarioDao.obtenerUsuarioPorId(usuarioDTO.getId());
        if (usuario == null)
            throw new NotFoundException("Usuario no encontrado");

        usuario.setIntentos(0);
        usuario.setEnabled(usuarioDTO.getEnabled());
        usuarioDao.crudUsuario(usuario, 2);
        return obtener(usuario.getId());
    }

    @Override
    public UsuarioDTO obtenerUsuarioPorLogin(String login) {
        Usuario usuario = usuarioDao.obtenerUsuarioPorLogin(login);
        return obtenerUsuario(usuario);
    }

    private UsuarioDTO obtenerUsuario(Usuario usuario){
        if (usuario == null)
            throw new NotFoundException("Usuario no encontrado");

        UsuarioDTO usuarioDTO = usuarioMapper.toDto(usuario);
        usuarioDTO.setClave(null);
        usuarioDTO.setFuncionario(funcionarioService.obtener(usuario.getFuncionarioId()));
        RolDTO rolDTO = rolService.obtenerRolPorUsuario(usuario.getId());
        if(rolDTO!=null)
            usuarioDTO.setRolId(rolDTO.getId());
        usuarioDTO.setArchivo(archivoUsuarioService.obtener(usuario.getId()));
        return usuarioDTO;
    }
}

