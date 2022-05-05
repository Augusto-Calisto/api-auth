package br.com.unip.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.unip.entity.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${auth.jwt.expiration}")
	private String expiration;
	
	@Value("${auth.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication auth) {
		Usuario usuario = (Usuario) auth.getPrincipal();
		
		Date hoje = new Date();
		
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration)); // 86400000 milisegundos = 1 dia
		
		return Jwts
				.builder() // Para criar o token
				.setIssuer("API-Auth-Unip") // Quem é a aplicação que está gerando o token
				.setSubject(usuario.getId().toString()) // Quem é o usuario deste token, pode ser o nome, id
				.setIssuedAt(hoje) // Qual foi a data da geração deste token
				.setExpiration(dataExpiracao) // Quando que expira o token
				.signWith(SignatureAlgorithm.HS256, secret) // Pela especificação do token, ele tem que ser criptografado
				.compact(); // Para transformar o token em string
	}

	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			
			return true;
			
		} catch(Exception e) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		String id = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
		
		return Long.parseLong(id);
	}
}