package br.com.systemsgs.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.systemsgs.mercadolivre.model.ModelUsuario;
import br.com.systemsgs.mercadolivre.service.UsuarioService;

@RestController
@RequestMapping(value = "/api/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping(value = "/salvar")
	public ResponseEntity<?> salvaUsuario(@RequestBody @Valid ModelUsuario modelUsuario) {
		usuarioService.salvaUsuario(modelUsuario);
		
		return ResponseEntity.ok("Usuário Salvo com Sucesso!!!");
	}

}