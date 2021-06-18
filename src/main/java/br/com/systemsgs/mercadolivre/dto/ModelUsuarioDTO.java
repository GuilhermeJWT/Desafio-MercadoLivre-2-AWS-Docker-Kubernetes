package br.com.systemsgs.mercadolivre.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.systemsgs.mercadolivre.annotation.GenericUniqueColumn;
import br.com.systemsgs.mercadolivre.model.ModelUsuario;

public class ModelUsuarioDTO {
	
	@GenericUniqueColumn(domainClass = ModelUsuario.class, fieldName = "login", message = "Login já Cadastrado, Informe Outro!!!")
	@Email(message = "Formato de E-mail Inválido, Informe Outro!!!")
	@NotBlank(message = "O Login deve ser Informado!!!")
	private String login;

	@Length(min = 6, message = "A Senha deve conter pelomenos 6 Caracteres!!!")
	@NotBlank(message = "A Senha deve ser Informada!!!")
	private String senha;
	
	public ModelUsuario converter() {
		return new ModelUsuario(this.login, new SenhaCriptografadaDTO(senha));
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
