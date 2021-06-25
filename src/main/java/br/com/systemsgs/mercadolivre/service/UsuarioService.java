package br.com.systemsgs.mercadolivre.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.systemsgs.mercadolivre.config.DozerConverter;
import br.com.systemsgs.mercadolivre.dto.ModelUsuarioDTO;
import br.com.systemsgs.mercadolivre.exception.UsuarioNaoEncontradoException;
import br.com.systemsgs.mercadolivre.model.ModelUsuario;
import br.com.systemsgs.mercadolivre.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Transactional
	public ModelUsuarioDTO salvaUsuario(ModelUsuarioDTO modelUsuarioDTO) {
		var modelUsuario = DozerConverter.converteEntidade(modelUsuarioDTO, ModelUsuario.class);
		var usuarioConvertido = DozerConverter.converteEntidade(usuarioRepository.save(modelUsuario), ModelUsuarioDTO.class);
		
		return usuarioConvertido;
	}
	
	@Transactional
	public void deleteUsuario(Long id) {
		usuarioRepository.findById(id).map(usuario -> {
			usuarioRepository.delete(usuario);
			return usuario;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não Encontrado!!!"));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var usuario = usuarioRepository.findByLogin(username);
		
		if(usuario != null) {
			return usuario;
		}else {
			throw new UsuarioNaoEncontradoException();
		}
	}

	public ModelUsuarioDTO pesquisaPorId(Long id) {
		var modelUsuario = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não Encontrado!!!"));
		return DozerConverter.converteEntidade(modelUsuario, ModelUsuarioDTO.class);
	}
}
