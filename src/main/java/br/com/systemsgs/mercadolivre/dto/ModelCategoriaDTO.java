package br.com.systemsgs.mercadolivre.dto;

import javax.validation.constraints.NotBlank;

import br.com.systemsgs.mercadolivre.annotation.GenericUniqueColumn;
import br.com.systemsgs.mercadolivre.model.ModelCategoria;

public class ModelCategoriaDTO {
	
	@GenericUniqueColumn(domainClass = ModelCategoria.class, fieldName = "nome", message = "Nome da Categoria já Cadastrado, Informe Outro!!!")
	@NotBlank(message = "O Nome da Categoria deve ser Informado!!!")
	private String nome;
	
	private Long idMae;

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
