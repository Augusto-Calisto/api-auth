package br.com.unip.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.unip.entity.Usuario;
import br.com.unip.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Optional<Usuario> findById(Long id) {
		return usuarioRepository.findById(id);
	}
	
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}
	
	public Usuario save(Usuario usuario) {
		String senhaCriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
		
		usuario.setSenha(senhaCriptografada);
		
		return usuarioRepository.save(usuario);
	}

	public void delete(Long id) {
		usuarioRepository.deleteById(id);
	}
}