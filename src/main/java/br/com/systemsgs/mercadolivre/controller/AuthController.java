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

import br.com.systemsgs.mercadolivre.dto.CredentialsUserDTO;
import br.com.systemsgs.mercadolivre.jwt.JwtTokenProvider;
import br.com.systemsgs.mercadolivre.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtTokenProvider tokenProvider;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	public AuthController(@Autowired AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider,UsuarioRepository usuarioRepository) {
		this.authenticationManager = authenticationManager;
		this.tokenProvider = tokenProvider;
		this.usuarioRepository = usuarioRepository;
	}
	

	@PostMapping(value = "/autenticar")
	public ResponseEntity autenticaUsuario(@RequestBody @Valid CredentialsUserDTO credentialsUserDTO) {
		try {
			var login = credentialsUserDTO.getLogin();
			var senha = credentialsUserDTO.getSenha();
			
			var usuarioLogado2 = usuarioRepository.findByLogin(login);
			
			System.out.println(usuarioLogado2.getLogin());
			System.out.println(usuarioLogado2.getSenha());
			
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
			exception.printStackTrace();
			throw new BadCredentialsException("Usuário ou Senha Inválidos!!!");
		}
	}

}
