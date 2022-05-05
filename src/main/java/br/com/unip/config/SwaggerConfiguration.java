package br.com.unip.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.unip.entity.Usuario;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {
	
	@Bean
	public Docket authApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.unip")) // Quero que leia todas as classes do projeto, mas, tambem tem annotation para ignorar um end-point
				.paths(PathSelectors.ant("/**")) // Quais end-points o Swagger tem analisar, neste caso /**, leia todos os end-points
				.build()
				.ignoredParameterTypes(Usuario.class) // Para ignorar nos endpoints os parâmetros relacionados a classe Usuario
				.globalOperationParameters(Arrays.asList(
						new ParameterBuilder()
						.name("Authorization")
						.description("Header para o token JWT")
						.modelRef(new ModelRef("string"))
						.parameterAccess("header")
						.required(false)
						.build()));
	}
}
