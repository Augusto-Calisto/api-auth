package br.com.unip.service;

import org.springframework.stereotype.Service;

import br.com.unip.model.Usuario;
import br.com.unip.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	private UsuarioRepository usuarioRepository;
	
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
}