package br.com.systemsgs.mercadolivre.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.systemsgs.mercadolivre.config.DozerConverter;
import br.com.systemsgs.mercadolivre.dto.ModelCaracteristicaDTO;
import br.com.systemsgs.mercadolivre.model.ModelCaracteristica;
import br.com.systemsgs.mercadolivre.repository.CaracteristicaRepository;

@Service
public class CaracteristicaService {
	
	@Autowired
	private CaracteristicaRepository caracteristicaRepository;

	@Transactional
	public ModelCaracteristicaDTO salvaCaracteristica(ModelCaracteristicaDTO modelCaracteristicaDTO) {
		var modelCaracteristica = DozerConverter.converteEntidade(modelCaracteristicaDTO, ModelCaracteristica.class);
		var caracteristicaConvertida = DozerConverter.converteEntidade(caracteristicaRepository.save(modelCaracteristica), ModelCaracteristicaDTO.class);
		
		return caracteristicaConvertida;
	}

	public ModelCaracteristicaDTO pesquisaPorId(Long id) {
		var modelCaracteristica = caracteristicaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Caracteristica não Encontrada!!!"));
		return DozerConverter.converteEntidade(modelCaracteristica, ModelCaracteristicaDTO.class);
	}

}
