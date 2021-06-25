package br.com.systemsgs.mercadolivre.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.systemsgs.mercadolivre.dto.ModelUsuarioDTO;
import br.com.systemsgs.mercadolivre.service.UsuarioService;

@RestController
@RequestMapping(value = "/api/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping(value = "/salvar")
	public ResponseEntity<?> salvaUsuario(@RequestBody @Valid ModelUsuarioDTO modelUsuarioDTO) {
		ModelUsuarioDTO usuario = usuarioService.salvaUsuario(modelUsuarioDTO);
		usuario.add(linkTo(methodOn(UsuarioController.class).pesquisaPorId(usuario.getKey())).withSelfRel());
		
		return ResponseEntity.ok("Usuário Salvo com Sucesso!!!");
	}
	
	@Cacheable(value = "cache-usuarios")
	@GetMapping(value = "/listaTodos")
	public List<ModelUsuarioDTO> listaTodos(){
		List<ModelUsuarioDTO> usuarios = usuarioService.listaTodos();
		usuarios.stream().forEach(u -> u.add(linkTo(methodOn(UsuarioController.class).pesquisaPorId(u.getKey())).withSelfRel()));
		
		return usuarios;
	}
	
	@Cacheable(value = "cache-pesquisa-usuario")
	@GetMapping(value = "/pesquisaPorId/{id}")
	public ModelUsuarioDTO pesquisaPorId(@PathVariable("id") Long id) {
		ModelUsuarioDTO modelUsuarioDTO = usuarioService.pesquisaPorId(id);
		modelUsuarioDTO.add(linkTo(methodOn(UsuarioController.class).pesquisaPorId(id)).withSelfRel());
		
		return modelUsuarioDTO;
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> deleteUsuario(@PathVariable("id") Long id) {
		usuarioService.deleteUsuario(id);
		
		return ResponseEntity.ok().build();
	}
	
}
