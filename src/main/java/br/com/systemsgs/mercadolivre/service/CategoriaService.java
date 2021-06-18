package br.com.systemsgs.mercadolivre.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.systemsgs.mercadolivre.config.DozerConverter;
import br.com.systemsgs.mercadolivre.dto.ModelCategoriaDTO;
import br.com.systemsgs.mercadolivre.model.ModelCategoria;
import br.com.systemsgs.mercadolivre.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Transactional
	public ModelCategoriaDTO salvaCategoria(ModelCategoriaDTO modelCategoriaDTO) {
		var modelCategoria = DozerConverter.converteEntidade(modelCategoriaDTO, ModelCategoria.class);
		var categoriaConvertida = DozerConverter.converteEntidade(categoriaRepository.save(modelCategoria), ModelCategoriaDTO.class);
		
		return categoriaConvertida;
	}

}
