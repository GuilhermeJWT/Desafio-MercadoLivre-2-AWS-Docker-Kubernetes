package br.com.systemsgs.mercadolivre.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.systemsgs.mercadolivre.dto.ModelCaracteristicaDTO;
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
	
	@GetMapping(value = "/pesquisaPorId/{id}")
	public ModelCaracteristicaDTO pesquisaPorId(@PathVariable("id") Long id) {
		ModelCaracteristicaDTO modelCaracteristicaDTO = caracteristicaService.pesquisaPorId(id);
		modelCaracteristicaDTO.add(linkTo(methodOn(CaracteristicaController.class).pesquisaPorId(id)).withSelfRel());
		
		return modelCaracteristicaDTO;
	}
	
}
