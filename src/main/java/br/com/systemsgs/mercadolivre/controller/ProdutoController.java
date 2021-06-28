package br.com.systemsgs.mercadolivre.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import javax.transaction.Transactional;
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

import br.com.systemsgs.mercadolivre.dto.ModelProdutoDTO;
import br.com.systemsgs.mercadolivre.service.ProdutoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@ApiOperation(value = "Endpoint Salva um Produto")
	@PostMapping(value = "/salvar")
	public ModelProdutoDTO salvaProduto(@RequestBody @Valid ModelProdutoDTO modelProdutoDTO) {
		ModelProdutoDTO produtoDTO = produtoService.salvaProduto(modelProdutoDTO);
		produtoDTO.add(linkTo(methodOn(ProdutoController.class).pesquisaPorId(produtoDTO.getKey())).withSelfRel());
		
		return produtoDTO;
	}
	
	@ApiOperation(value = "Endpoint Lista todos Produtos")
	@Cacheable(value = "cache-produtos")
	@GetMapping(value = "/listaTodos")
	public List<ModelProdutoDTO> listaTodos() {
		List<ModelProdutoDTO> produtos = produtoService.listaTodos();
		produtos.stream().forEach(p -> p.add(linkTo(methodOn(ProdutoController.class).pesquisaPorId(p.getKey())).withSelfRel()));
		
		return produtos;
	}
	
	@ApiOperation(value = "Endpoint Pesquisa por Id Produto")
	@Cacheable(value = "cache-pesquisa-produto")
	@GetMapping(value = "/pesquisaPorId/{id}")
	public ModelProdutoDTO pesquisaPorId(@PathVariable("id") Long id) {
		ModelProdutoDTO modelProdutoDTO = produtoService.pesquisaPorId(id);
		modelProdutoDTO.add(linkTo(methodOn(UsuarioController.class).pesquisaPorId(id)).withSelfRel());
		
		return modelProdutoDTO;
	}
	
	@ApiOperation(value = "Endpoint Deleta por Id Produto")
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> deleteProduto (@PathVariable("id") Long id){
		produtoService.deleteProduto(id);
		
		return ResponseEntity.ok().build();
	}

}
