package br.com.systemsgs.mercadolivre.config;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import br.com.systemsgs.mercadolivre.dto.ModelMercadoLivreDTO;

@Component
@Endpoint(id = "mercadolivre-guilhersantos", enableByDefault = true)
public class CustomizeEndpointMercadoLivre {
	
	@ReadOperation
	public ModelMercadoLivreDTO retornaHealthCheck() {
		return new ModelMercadoLivreDTO(12345, "Mercado Livre", "Ativo! Tudo Funcionando!");
	}

}
