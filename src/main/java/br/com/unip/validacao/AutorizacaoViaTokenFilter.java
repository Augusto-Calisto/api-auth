package br.com.unip.validacao;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.unip.model.Usuario;
import br.com.unip.repository.UsuarioRepository;
import br.com.unip.service.TokenService;

public class AutorizacaoViaTokenFilter extends OncePerRequestFilter {
	private TokenService tokenService;
	private UsuarioRepository usuarioRepository;
	
	public AutorizacaoViaTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	/* Filtro do spring chamado a cada requisição */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String token = recuperarToken(request); // A autenticação é feita para cada requisição
		
		if(tokenService.isTokenValido(token)) {
			autenticarCliente(token);
		}
		
		filterChain.doFilter(request, response); // Depois de verificar tudo, segue o fluxo
	}

	private void autenticarCliente(String token) {
		Long id = tokenService.getIdUsuario(token);
		
		Usuario usuario = usuarioRepository.findById(id).get();
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return "";
		}
		
		return token.substring(7, token.length());
	}
}
