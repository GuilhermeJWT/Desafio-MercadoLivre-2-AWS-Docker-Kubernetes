package br.com.systemsgs.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.systemsgs.mercadolivre.model.ModelCaracteristicaDTO;
import br.com.systemsgs.mercadolivre.service.CaracteristicaService;

@RestController
@RequestMapping(value = "/api/caracteristica")
public class CaracteristicaController {
	
	@Autowired
	private CaracteristicaService caracteristicaService;
	
	@PostMapping(value = "/salvar")
	public ModelCaracteristicaDTO salvaCaracteristica(@RequestBody @Valid ModelCaracteristicaDTO modelCaracteristicaDTO){
		return caracteristicaService.salvaCaracteristica(modelCaracteristicaDTO);
	}
	
}
