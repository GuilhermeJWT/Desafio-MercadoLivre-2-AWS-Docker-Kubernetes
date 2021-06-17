package br.com.systemsgs.mercadolivre.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.systemsgs.mercadolivre.model.ModelUsuario;
import br.com.systemsgs.mercadolivre.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional
	public ModelUsuario salvaUsuario(ModelUsuario modelUsuario) {
		return usuarioRepository.save(modelUsuario);
	}
	
}
