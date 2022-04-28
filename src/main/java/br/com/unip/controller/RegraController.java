package br.com.unip.controller;

import java.util.List;
import java.util.Optional;

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

import br.com.unip.model.Regra;
import br.com.unip.service.RegraService;

@RestController
@RequestMapping("regra")
public class RegraController {
	
	@Autowired
	private RegraService regraService;
	
	@GetMapping("{id}")
	public ResponseEntity<Regra> buscarRegra(@PathVariable("id") Long id) {
		Optional<Regra> optional = regraService.findById(id);
		
		if(optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("all")
	public ResponseEntity<List<Regra>> buscarTodasAsRegras() {
		List<Regra> regras = regraService.findAll();
		
		return ResponseEntity.ok(regras);
	}
	
	@PostMapping("save")
	public ResponseEntity<Regra> cadastrarRegra(@RequestBody Regra regra) {
		Regra regraSalva = regraService.save(regra);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(regraSalva);
	}
	
	@PutMapping("update")
	public ResponseEntity<Regra> atualizarRegra(@RequestBody Regra regra) {
		Regra regraAtualizada = regraService.save(regra);
		
		return ResponseEntity.ok(regraAtualizada);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<HttpStatus> excluirRegra(@PathVariable("id") Long id) {
		regraService.delete(id);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}