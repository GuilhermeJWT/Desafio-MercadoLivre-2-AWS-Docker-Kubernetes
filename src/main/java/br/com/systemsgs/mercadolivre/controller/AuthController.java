package br.com.systemsgs.mercadolivre.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.systemsgs.mercadolivre.dto.ModelUsuarioDTO;
import br.com.systemsgs.mercadolivre.jwt.JwtTokenProvider;
import br.com.systemsgs.mercadolivre.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping(value = "/autenticar")
	public ResponseEntity autenticaUsuario(@RequestBody @Valid ModelUsuarioDTO modelUsuarioDTO) {
		try {
			var login = modelUsuarioDTO.getLogin();
			var senha = modelUsuarioDTO.getSenha();
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, senha));
			var usuarioLogado = usuarioRepository.findByLogin(login);
			
			var token = "";
			
			if(usuarioLogado != null) {
				token = tokenProvider.createToken(login, usuarioLogado.getRoles());
			}else {
				throw new UsernameNotFoundException("Login: " + login + " não Encontrado!!!");
			}
			
			Map<Object, Object> map = new HashMap<>();
			map.put("login", login);
			map.put("token", token);
			
			return ok(map);
		}catch (AuthenticationException exception) {
			throw new BadCredentialsException("Usuário ou Senha Inválidos!!!");
		}
	}

}
