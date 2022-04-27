package br.com.unip.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.unip.dto.TokenDTO;
import br.com.unip.model.FormLogin;
import br.com.unip.service.TokenService;

@RestController
@RequestMapping("auth")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDTO> autenticar(@Valid @RequestBody FormLogin form) {
		UsernamePasswordAuthenticationToken dadosForm = form.converter();
		
		try {
			Authentication auth = authManager.authenticate(dadosForm);
			
			String token = tokenService.gerarToken(auth);
			
			return ResponseEntity.ok(new TokenDTO("Bearer", token));
			
		} catch(AuthenticationException erro) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
}
