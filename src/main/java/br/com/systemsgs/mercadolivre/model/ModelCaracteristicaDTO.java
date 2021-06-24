package br.com.systemsgs.mercadolivre.model;

import javax.validation.constraints.NotBlank;

public class ModelCaracteristicaDTO {
	
	@NotBlank(message = "O Nome deve ser Informado!!!")
	private String nome;
	
	@NotBlank(message = "A Descrição deve ser Informada!!!")
	private String descricao;
	
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
