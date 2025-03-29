package com.votacao.votacao_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.ExternalDocumentation;

@SpringBootApplication
public class VotacaoApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(VotacaoApiApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("API de Votação")
						.description("API para gerenciamento de pautas, sessões de votação e votos.")
						.version("1.0.0")
						.termsOfService("Meus termos")
						.contact(new Contact()
								.name("Joao maria")
								.email("joaomaria2006@gmail.com@gmail.com")
								.url("https://www.linkedin.com/in/joaomariak/") // Opcional
						)
						.license(new License()
								.name("Apache 2.0")
								.url("https://www.apache.org/licenses/LICENSE-2.0.html")
						)
				)
				.externalDocs(new ExternalDocumentation()
						.description("Documentação completa da API de Votação")
						.url("https://github.com/dbserver/desafio-votacao")
				);
	}

}
