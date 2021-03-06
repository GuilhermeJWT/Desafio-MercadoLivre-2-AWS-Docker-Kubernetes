package br.com.systemsgs.mercadolivre.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

	public List<ModelCategoriaDTO> listarTodasCategorias() {
		return DozerConverter.converteList(categoriaRepository.findAll(), ModelCategoriaDTO.class);
	}
	
	@Transactional
	public void deletaCategoria(Long id) {
		categoriaRepository.findById(id).map(categoria -> {
			categoriaRepository.delete(categoria);
			return categoria;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não Encontrada!!!"));
	}

	public ModelCategoriaDTO pesquisaPorId(Long id) {
		var modelCategoria = categoriaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Categoria não Encontrada!!!"));
		return DozerConverter.converteEntidade(modelCategoria, ModelCategoriaDTO.class);
	}

}
