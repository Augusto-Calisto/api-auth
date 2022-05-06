package br.com.unip.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	
	@Bean
	public Docket authApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(apiKey())
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.unip")) // Quero que leia todas as classes do projeto, mas, tambem tem annotation para ignorar um end-point
				.paths(PathSelectors.any()) // Quais end-points o Swagger tem analisar, leia todos os end-points
				.build()
				.useDefaultResponseMessages(false)
				.produces(Collections.singleton("application/json"))
                .consumes(Collections.singleton("application/json"));
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
							.title("API Auth")
							.version("1.0")
							.description("Simular uma API de Autenticação")
							.build();
	}
	
	private List<SecurityScheme> apiKey() {
		List<SecurityScheme> schemeList = new ArrayList<>();
		
		schemeList.add(new ApiKey("JWT", HttpHeaders.AUTHORIZATION, "header"));
		
        return schemeList;
    }
	
	private SecurityContext securityContext() {
		return SecurityContext.builder()
								.securityReferences(tokenAuth())
								.build();
	}
	
	private List<SecurityReference> tokenAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		
        authorizationScopes[0] = authorizationScope;
        
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}
}