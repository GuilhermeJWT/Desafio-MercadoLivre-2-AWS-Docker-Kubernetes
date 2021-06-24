package br.com.systemsgs.mercadolivre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "produto")
public class ModelProduto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O Nome deve ser Informado!!!")
	private String nome;
	
	@Length(min = 0, message = "A Quantidade deve ser Maior ou Igual a 0 !!!")
	@NotNull(message = "A Quantidade deve ser Informada!!!")
	private int quantidade;
	
	@Length(max = 1000, message = "A Descrição deve ter no Máximo 1000 Caracteres!!!")
	@NotBlank(message = "A Descrição deve ser Informada!!!")
	private String descricao;
	
	private LocalDateTime instanteCadastro = LocalDateTime.now();
	
	@Positive(message = "O Valor deve ser Maior que 0 !!!")
	@NotNull(message = "O Valor deve ser Informado!!!")
	private BigDecimal valor;
	
	@OneToMany(mappedBy = "produto", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ModelCaracteristica> caracteristicas = new ArrayList<>();
	
	@OneToOne(optional = false ,cascade = CascadeType.ALL)
	private ModelCategoria categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public LocalDateTime getInstanteCadastro() {
		return instanteCadastro;
	}

	public void setInstanteCadastro(LocalDateTime instanteCadastro) {
		this.instanteCadastro = instanteCadastro;
	}

	public List<ModelCaracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(List<ModelCaracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public ModelCategoria getCategoria() {
		return categoria;
	}

	public void setCategoria(ModelCategoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelProduto other = (ModelProduto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
