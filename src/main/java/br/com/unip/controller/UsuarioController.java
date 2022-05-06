package br.com.unip.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.unip.dto.UsuarioDTO;
import br.com.unip.entity.Regra;
import br.com.unip.entity.Usuario;
import br.com.unip.service.RegraService;
import br.com.unip.service.UsuarioService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RegraService regraService;
	
	@ApiOperation(value = "Buscar o usuaraio específico pelo id")
	@GetMapping("{id}")
	public ResponseEntity<Usuario> buscarUsuario(@PathVariable("id") Long id) {
		Optional<Usuario> optional = usuarioService.findById(id);
		
		if(optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@ApiOperation(value = "Buscar todos os usuarios")
	@GetMapping("all")
	public ResponseEntity<List<Usuario>> buscarTodosUsuarios() {
		List<Usuario> usuarios = usuarioService.findAll();
		
		return ResponseEntity.ok(usuarios);
	}
	
	@ApiOperation(value = "Incluir um usuario")
	@PostMapping("save")
	public ResponseEntity<?> cadastrarUsuario(@Valid @RequestBody UsuarioDTO usuarioDto) {
		try {
			Usuario usuario = Usuario.converterToUsuario(usuarioDto);
		
			List<Regra> regras = regraService.saveAll(usuarioDto.getRegras());
			
			usuario.setRegras(regras);
			
			usuarioService.save(usuario);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDto);
			
		} catch(IllegalArgumentException erro) {
			return ResponseEntity.badRequest().body(erro.getMessage());
		}
	}
	
	@ApiOperation(value = "Atualizar alguma informação do usuario")
	@PutMapping("update")
	public ResponseEntity<Usuario> atualizarUsuario(@RequestBody Usuario usuario) {
		Usuario usuarioAtualizado = usuarioService.save(usuario);
		
		return ResponseEntity.ok(usuarioAtualizado);
	}
	
	@ApiOperation(value = "Excluir usuario")
	@DeleteMapping("{id}")
	public ResponseEntity<Usuario> excluirUsuario(@PathVariable("id") Long id) {
		usuarioService.delete(id);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}