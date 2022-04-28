package br.com.unip.dto;

import java.io.Serializable;
import java.util.List;

import br.com.unip.model.Regra;
import br.com.unip.model.Usuario;

public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String email;
	private String senha;
	private List<Regra> regras;
	
	public static Usuario converterToUsuario(UsuarioDTO usuarioDto) {
		Usuario usuario = new Usuario(usuarioDto.getNome(), usuarioDto.getEmail(), usuarioDto.getSenha(), usuarioDto.getRegras());
		
		return usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Regra> getRegras() {
		return regras;
	}

	public void setRegras(List<Regra> regras) {
		this.regras = regras;
	}

	@Override
	public String toString() {
		return "[nome=" + nome + ", email=" + email + ", regras=" + regras + "]";
	}
}