package br.com.systemsgs.mercadolivre.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.hateoas.ResourceSupport;

import com.github.dozermapper.core.Mapping;

public class ModelCaracteristicaDTO extends ResourceSupport{

	@Mapping(value = "id")
	private Long key;
	
	@NotBlank(message = "O Nome deve ser Informado!!!")
	private String nome;

	@NotBlank(message = "A Descrição deve ser Informada!!!")
	private String descricao;
	
	public Long getKey() {
		return key;
	}
	
	public void setKey(Long key) {
		this.key = key;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
