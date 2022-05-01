package br.com.unip.dto;

import java.io.Serializable;

public class TokenDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String tipo;
	private String token;

	public TokenDTO(String tipo, String token) {
		this.tipo = tipo;
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
	}
}