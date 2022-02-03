package com.velacuss.backend.auth.event;

import com.velacuss.backend.dao.UsuarioDao;
import com.velacuss.backend.domain.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

	private Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);

	@Autowired
	private UsuarioDao usuarioDao;

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		//UserDetails user = (UserDetails) authentication.getPrincipal();

		String mensaje = "Success Login: " + authentication.getName();
		log.info(mensaje);

		Usuario usuario = usuarioDao.obtenerUsuarioPorLogin(authentication.getName());

		if(usuario != null && usuario.getIntentos() != null && usuario.getIntentos() > 0) {
			usuario.setIntentos(0);
			usuarioDao.crudUsuario(usuario, 2);
		}
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		String mensaje = "Error en el Login: " + exception.getMessage();
		log.error(mensaje);

		Usuario usuario = usuarioDao.obtenerUsuarioPorLogin(authentication.getName());

		if(usuario!=null) {
			if (usuario.getIntentos() == null) {
				usuario.setIntentos(0);
			}

			usuario.setIntentos(usuario.getIntentos() + 1);

			if (usuario.getIntentos() >= 5) {
				usuario.setEnabled(false);
			}

			usuarioDao.crudUsuario(usuario, 2);
		}
	}
}
