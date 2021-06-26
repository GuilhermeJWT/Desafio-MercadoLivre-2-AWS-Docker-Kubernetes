package br.com.systemsgs.mercadolivre.dto;

public class ModelMercadoLivreDTO {

	private int id;
	private String nome;
	private String status;

	public ModelMercadoLivreDTO(int id, String nome, String status) {
		this.id = id;
		this.nome = nome;
		this.status = status;
	}
	
	public ModelMercadoLivreDTO() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
