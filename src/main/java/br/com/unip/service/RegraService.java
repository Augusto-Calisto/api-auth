package br.com.unip.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unip.entity.Regra;
import br.com.unip.repository.RegraRepository;

@Service
public class RegraService {
	
	@Autowired
	private RegraRepository regraRepository;
	
	public Optional<Regra> findById(Long id) {
		return regraRepository.findById(id);
	}
	
	public List<Regra> findAll() {
		return regraRepository.findAll();
	}
	
	public Regra save(Regra regra) {
		return regraRepository.save(regra);
	}
	
	public List<Regra> saveAll(List<Regra> regras) throws IllegalArgumentException {
		List<Regra> regrasSalvas = regras.stream()
										.map(regra -> {
											String nome = regra.getNome();
											
											if(!existsThisRole(nome)) {
												throw new IllegalArgumentException("A regra '" + nome + "' nao existe, cadastrar primeiramente!!!");
											}
											
											return findByNome(nome);
										})
										.collect(Collectors.toList());
		
		return regrasSalvas;
	}
	
	public void delete(Long id) {
		regraRepository.deleteById(id);
	}
	
	private boolean existsThisRole(String role) {
		Regra regra = findByNome(role);
		
		if(regra != null) {
			return true;
		}
		
		return false;
	}
	
	public Regra findByNome(String nome) {
		return regraRepository.findByNome(nome);
	}
}