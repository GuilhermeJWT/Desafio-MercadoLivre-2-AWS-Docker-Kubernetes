package br.com.systemsgs.mercadolivre.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

}
