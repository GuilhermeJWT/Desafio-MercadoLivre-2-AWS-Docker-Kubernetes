package br.com.systemsgs.mercadolivre.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.systemsgs.mercadolivre.config.DozerConverter;
import br.com.systemsgs.mercadolivre.dto.ModelProdutoDTO;
import br.com.systemsgs.mercadolivre.model.ModelProduto;
import br.com.systemsgs.mercadolivre.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Transactional
	public ModelProdutoDTO salvaProduto(ModelProdutoDTO modelProdutoDTO) {
		var modelProduto = DozerConverter.converteEntidade(modelProdutoDTO, ModelProduto.class);
		var produtoConvertido = DozerConverter.converteEntidade(produtoRepository.save(modelProduto), ModelProdutoDTO.class);
		
		return produtoConvertido;
	}

	public ModelProdutoDTO pesquisaPorId(Long id) {
		var modelProduto = produtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto não Encontrado!!!"));
		return DozerConverter.converteEntidade(modelProduto, ModelProdutoDTO.class);
	}

}
