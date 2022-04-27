package br.com.unip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class AuthApplication {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext contexto = SpringApplication.run(AuthApplication.class, args);
		
		ConfigurableEnvironment properties = contexto.getEnvironment();
		
		int porta = properties.getProperty("server.port", Integer.class);
		
		System.out.println("Acessar a documentacao do swagger em: http://localhost:" + porta + "/swagger-ui.html");
	}
}