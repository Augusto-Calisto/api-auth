package br.com.unip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
public class AuthApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext contexto = SpringApplication.run(AuthApplication.class, args);
		
		ConfigurableEnvironment properties = contexto.getEnvironment();
		
		String porta = properties.getProperty("server.port");
		
		System.out.println("Acessar a documentacao do swagger em: http://localhost:" + porta + "/swagger-ui.html");
	}
}