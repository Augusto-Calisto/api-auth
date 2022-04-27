package br.com.unip.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.unip.repository.UsuarioRepository;
import br.com.unip.service.AuthenticationService;
import br.com.unip.service.TokenService;
import br.com.unip.validacao.AutorizacaoViaTokenFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationService authentication;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	// Configurações de Autenticação [Controle de acesso, login ...]
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authentication).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	// Configurações de Autorização [URL's, quem pode acessar cada URL, perfil de acesso, as ROLES]
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
					.antMatchers(HttpMethod.GET, "/documentacao").permitAll()
					.antMatchers(HttpMethod.POST, "/auth").permitAll()
					.antMatchers(HttpMethod.GET, "/h2-console/**").permitAll()
					.antMatchers(HttpMethod.POST, "/usuario/cadastrar").hasRole("ADM") // Apenas perfil ADM, pode cadastrar 
				.anyRequest().authenticated()
			.and()
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Avisando ao spring security que não é para criar uma sessão, pois, utilizaremos, TOKEN
			.and()
				.addFilterBefore(new AutorizacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
	}
	
	// Configuração de recursos estáticos, para o security ignorar os recursos como HTML, CSS, JS, imagens, ...
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
		   .ignoring()
		   .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**", "/h2-console/**", "/error");
	}
}