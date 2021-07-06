package br.com.systemsgs.mercadolivre.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.systemsgs.mercadolivre.dto.ModelCategoriaDTO;
import br.com.systemsgs.mercadolivre.service.CategoriaService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@ApiOperation(value = "Endpoint Salva uma Categoria")
	@PostMapping(value = "/salvar")
	public ModelCategoriaDTO salvaCategoria(@RequestBody @Valid ModelCategoriaDTO modelCategoriaDTO){
		ModelCategoriaDTO categoriaDTO = categoriaService.salvaCategoria(modelCategoriaDTO);
		categoriaDTO.add(linkTo(methodOn(CategoriaController.class).pesquisaPorId(modelCategoriaDTO.getKey())).withSelfRel());
		
		return categoriaDTO;
	}
	
	@ApiOperation(value = "Endpoint Lista todas Categorias")
	@Cacheable(value = "cache-categoria")
	@GetMapping(value = "/listarTodas", produces = {"application/json", "application/xml"})
	public List<ModelCategoriaDTO> listaCategoria(){
		List<ModelCategoriaDTO> categorias = categoriaService.listarTodasCategorias(); 
		categorias.stream().forEach(c -> c.add(linkTo(methodOn(CategoriaController.class).pesquisaPorId(c.getKey())).withSelfRel()));
		
		return categorias;
	}
	
	@ApiOperation(value = "Endpoint Pesquisa por Id Categoria")
	@Cacheable(value = "cache-pesquisa-categoria")
	@GetMapping(value = "/pesquisaPorId/{id}", produces = {"application/json", "application/xml"})
	public ModelCategoriaDTO pesquisaPorId(@PathVariable("id") Long id) {
		ModelCategoriaDTO modelCategoriaDTO = categoriaService.pesquisaPorId(id);
		modelCategoriaDTO.add(linkTo(methodOn(CategoriaController.class).pesquisaPorId(id)).withSelfRel());
		
		return modelCategoriaDTO;
	}
	
	@ApiOperation(value = "Endpoint Deleta por Id Categoria")
	@DeleteMapping(value = "/delete/{id}")
	public void deletaCategoria(@PathVariable("id") Long id) {
		categoriaService.deletaCategoria(id);
	}

}
