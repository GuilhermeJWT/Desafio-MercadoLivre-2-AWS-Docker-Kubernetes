package br.com.systemsgs.mercadolivre.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaCriptografadaDTO {
	
	@Size(min = 6, message = "A Senha precisa ter no minimo 6 Caracteres!")
	@NotBlank(message = "A Senha deve ser Informada!")
	private String senha;

	public SenhaCriptografadaDTO(String senha) {
		this.senha = senha;
	}

	public String criptografaSenha() {
		return new BCryptPasswordEncoder().encode(senha);
	}

}
