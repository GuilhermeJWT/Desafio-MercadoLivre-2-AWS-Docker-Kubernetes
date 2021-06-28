package br.com.systemsgs.mercadolivre.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Collections;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class EnableSwaggerConfiguration {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.systemsgs.mercadolivre"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(informacoesApi());
	}

	private ApiInfo informacoesApi() {
		return new ApiInfo("API's Mercado Livre",
				"Disponibilizando recursos das Apis Livres",
				"V1", "Api - Livre",
				new Contact("Guilherme Santos", "https://github.com/GuilhermeJWT/Desafio-MercadoLivre-2-AWS-Docker-Kubernetes", "guilhermeteste@gmail.com"),
				null, null, Collections.emptyList());
	}

}
