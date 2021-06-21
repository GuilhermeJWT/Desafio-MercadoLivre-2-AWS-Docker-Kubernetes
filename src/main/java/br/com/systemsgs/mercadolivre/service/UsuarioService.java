package br.com.systemsgs.mercadolivre.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.systemsgs.mercadolivre.config.DozerConverter;
import br.com.systemsgs.mercadolivre.dto.ModelUsuarioDTO;
import br.com.systemsgs.mercadolivre.model.ModelUsuario;
import br.com.systemsgs.mercadolivre.repository.UsuarioRepository;

@Service
public class UsuarioService{
	
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

	/*
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var usuario = usuarioRepository.findByLogin(username);
		
		if(usuario != null) {
			return usuario;
		}else {
			throw new UsuarioNaoEncontradoException("Usuário ou Senha Inválidos!!!");
		}
	}
	*/
	
}
