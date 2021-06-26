package br.com.systemsgs.mercadolivre.config;

import java.net.URL;
import java.net.URLConnection;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthCheckConfiguration implements HealthIndicator {

	@Override
	public Health health() {
		return testaConexaoInternetAplicacaoMercadoLivre() == true ?
		Health.up().withDetail("Tudo Certo!", "Internet Mercado Livre Conectada!!!").build()
	    :Health.down().withDetail("Ops deu Erro!", "ERRO - Internet Fora - Mercado Livre Inátivo!!!").build();
	}

	private boolean testaConexaoInternetAplicacaoMercadoLivre() {
		boolean valida = false;

		try {
			/*Aqui vai ser a URL quando nosso Projeto estiver na AWS!*/
			URL url = new URL("https://www.itau.com.br");
			URLConnection conecta = url.openConnection();
			conecta.connect();
			
			valida = true;
		} catch (Exception exception) {
			valida = false;
		}

		return valida;

	}

}
