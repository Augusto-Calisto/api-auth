package br.com.unip.dto;

public class TokenDTO {
	private String token;
	private String tipo;

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