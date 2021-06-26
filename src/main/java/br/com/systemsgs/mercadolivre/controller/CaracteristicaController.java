package br.com.systemsgs.mercadolivre.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		ModelCaracteristicaDTO caracteristicaDTO = caracteristicaService.salvaCaracteristica(modelCaracteristicaDTO);
		caracteristicaDTO.add(linkTo(methodOn(CategoriaController.class).pesquisaPorId(modelCaracteristicaDTO.getKey())).withSelfRel());
		
		return caracteristicaDTO;
	}
	
	@Cacheable(value = "cache-caracteristica")
	@GetMapping(value = "/listaTodas")
	public List<ModelCaracteristicaDTO> listaCaracteristicas(){
		List<ModelCaracteristicaDTO> caracteristicas = caracteristicaService.listaCaracteristica();
		caracteristicas.stream().forEach(c -> c.add(linkTo(methodOn(CaracteristicaController.class).pesquisaPorId(c.getKey())).withSelfRel()));
		
		return caracteristicas;
	}
	
	@Cacheable(value = "cache-pesquisa-caracteristica")
	@GetMapping(value = "/pesquisaPorId/{id}")
	public ModelCaracteristicaDTO pesquisaPorId(@PathVariable("id") Long id) {
		ModelCaracteristicaDTO modelCaracteristicaDTO = caracteristicaService.pesquisaPorId(id);
		modelCaracteristicaDTO.add(linkTo(methodOn(CaracteristicaController.class).pesquisaPorId(id)).withSelfRel());
		
		return modelCaracteristicaDTO;
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> deletaCaracteristica(@PathVariable("id") Long id){
		caracteristicaService.deletaCaracteristica(id);
		
		return ResponseEntity.ok().build();
	}
	
}
