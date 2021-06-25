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

import br.com.systemsgs.mercadolivre.dto.ModelCategoriaDTO;
import br.com.systemsgs.mercadolivre.model.ModelCategoria;
import br.com.systemsgs.mercadolivre.service.CategoriaService;

@RestController
@RequestMapping(value = "/api/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@PostMapping(value = "/salvar")
	public ResponseEntity<?> salvaCategoria(@RequestBody @Valid ModelCategoriaDTO modelCategoriaDTO){
		categoriaService.salvaCategoria(modelCategoriaDTO);
		
		return ResponseEntity.ok("Categoria Salva com Sucesso!!!");
	}
	
	@Cacheable(value = "cache-categoria")
	@GetMapping(value = "/listarTodas")
	public List<ModelCategoria> listaCategoria(){
		return categoriaService.listarTodasCategorias();
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public void deletaCategoria(@PathVariable("id") Long id) {
		categoriaService.deletaCategoria(id);
	}
	
	@GetMapping(value = "/pesquisaPorId/{id}")
	public ModelCategoriaDTO pesquisaPorId(@PathVariable("id") Long id) {
		ModelCategoriaDTO modelCategoriaDTO = categoriaService.pesquisaPorId(id);
		modelCategoriaDTO.add(linkTo(methodOn(CategoriaController.class).pesquisaPorId(id)).withSelfRel());
		
		return modelCategoriaDTO;
	}

}
