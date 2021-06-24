package br.com.systemsgs.mercadolivre.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

public class ModelProdutoDTO {
	
	@NotBlank(message = "O Nome deve ser Informado!!!")
	private String nome;
	
	@Length(min = 0, message = "A Quantidade deve ser Maior ou Igual a 0 !!!")
	@NotNull(message = "A Quantidade deve ser Informada!!!")
	private int quantidade;
	
	@Length(max = 1000, message = "A Descrição deve ter no Máximo 1000 Caracteres!!!")
	@NotBlank(message = "A Descrição deve ser Informada!!!")
	private String descricao;
	
	@Positive(message = "O Valor deve ser Maior que 0 !!!")
	@NotNull(message = "O Valor deve ser Informado!!!")
	private BigDecimal valor;
	
	@NotNull(message = "A Categoria deve ser Informada!!!")
	private Long idCategoria;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}
	
}
