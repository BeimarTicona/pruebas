package com.velacuss.backend.auth;

import com.velacuss.backend.dao.FuncionarioDao;
import com.velacuss.backend.dao.RolDao;
import com.velacuss.backend.dao.UsuarioDao;
import com.velacuss.backend.domain.Funcionario;
import com.velacuss.backend.domain.Usuario;
import com.velacuss.backend.domain.dto.ArchivoUsuarioDTO;
import com.velacuss.backend.domain.dto.RolDTO;
import com.velacuss.backend.domain.dto.SucursalDTO;
import com.velacuss.backend.service.ArchivoUsuarioService;
import com.velacuss.backend.service.SucursalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InfoAdicionalToken implements TokenEnhancer {

    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private FuncionarioDao funcionarioDao;
    @Autowired
    private RolDao rolDao;
    @Autowired
    private SucursalService sucursalService;
    @Autowired
    private ArchivoUsuarioService archivoUsuarioService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {

        Usuario usuario = usuarioDao.obtenerUsuarioPorLogin(oAuth2Authentication.getName());
        Funcionario funcionario = funcionarioDao.obtenerFuncionarioPorId(usuario.getFuncionarioId());
        RolDTO rol = rolDao.obtenerRolDTOPorUsuario(usuario.getId());
        SucursalDTO sucursal = sucursalService.obtener(usuario.getSucursalId());
        ArchivoUsuarioDTO archivo = archivoUsuarioService.obtener(usuario.getId());

        Map<String, Object> info = new HashMap<>();
        info.put("nombre", funcionario.getNombre());
        info.put("primer_apellido", funcionario.getPrimerApellido());
        info.put("segundo_apellido", funcionario.getSegundoApellido());
        info.put("cargo", funcionario.getCargo());
        info.put("rol", rol);
        info.put("sucursal", sucursal);
        info.put("archivo", archivo);
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(info);

        return oAuth2AccessToken;
    }
}
