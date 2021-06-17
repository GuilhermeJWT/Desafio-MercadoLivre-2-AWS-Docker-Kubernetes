package br.com.systemsgs.mercadolivre.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.systemsgs.mercadolivre.config.DozerConverter;
import br.com.systemsgs.mercadolivre.dto.ModelUsuarioDTO;
import br.com.systemsgs.mercadolivre.model.ModelUsuario;
import br.com.systemsgs.mercadolivre.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional
	public ModelUsuarioDTO salvaUsuario(ModelUsuarioDTO modelUsuarioDTO) {
		var modelUsuario = DozerConverter.converteEntidade(modelUsuarioDTO, ModelUsuario.class);
		var usuarioConvertido = DozerConverter.converteEntidade(usuarioRepository.save(modelUsuario), ModelUsuarioDTO.class);
		
		return usuarioConvertido;
	}
	
}
