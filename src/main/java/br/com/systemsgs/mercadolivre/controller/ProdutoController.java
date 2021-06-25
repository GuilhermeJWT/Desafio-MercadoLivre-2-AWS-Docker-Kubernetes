package br.com.systemsgs.mercadolivre.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.systemsgs.mercadolivre.dto.ModelProdutoDTO;
import br.com.systemsgs.mercadolivre.service.ProdutoService;

@RestController
@RequestMapping(value = "/api/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@PostMapping(value = "/salvar")
	@Transactional
	public ModelProdutoDTO salvaProduto(@RequestBody @Valid ModelProdutoDTO modelProdutoDTO) {
		
		return produtoService.salvaProduto(modelProdutoDTO);
	}
	
	@GetMapping(value = "/pesquisaPorId/{id}")
	public ModelProdutoDTO pesquisaPorId(@PathVariable("id") Long id) {
		ModelProdutoDTO modelProdutoDTO = produtoService.pesquisaPorId(id);
		modelProdutoDTO.add(linkTo(methodOn(UsuarioController.class).pesquisaPorId(id)).withSelfRel());
		
		return modelProdutoDTO;
	}

}
