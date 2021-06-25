package br.com.systemsgs.mercadolivre.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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

}
