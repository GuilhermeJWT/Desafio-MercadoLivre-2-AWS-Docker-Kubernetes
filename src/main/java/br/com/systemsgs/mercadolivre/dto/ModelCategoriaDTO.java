package br.com.systemsgs.mercadolivre.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.hateoas.ResourceSupport;

import com.github.dozermapper.core.Mapping;

import br.com.systemsgs.mercadolivre.annotation.GenericUniqueColumn;
import br.com.systemsgs.mercadolivre.model.ModelCategoria;

public class ModelCategoriaDTO extends ResourceSupport{
	
	@Mapping(value = "id")
	private Long key;
	
	@GenericUniqueColumn(domainClass = ModelCategoria.class, fieldName = "nome", message = "Nome da Categoria já Cadastrado, Informe Outro!!!")
	@NotBlank(message = "O Nome da Categoria deve ser Informado!!!")
	private String nome;
	
	private Long idMae;
	
	public ModelCategoriaDTO(String nome, Long idMae) {
		this.nome = nome;
		this.idMae = idMae;
	}
	
	@Deprecated
	public ModelCategoriaDTO() {
		
	}
	
	public Long getKey() {
		return key;
	}
	
	public void setKey(Long key) {
		this.key = key;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getIdMae() {
		return idMae;
	}

	public void setIdMae(Long idMae) {
		this.idMae = idMae;
	}
	
}
