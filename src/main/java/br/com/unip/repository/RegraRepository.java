package br.com.unip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.unip.entity.Regra;

@Repository
public interface RegraRepository extends JpaRepository<Regra, Long> {
	Regra findByNome(String nome);
}