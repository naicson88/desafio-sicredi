package com.sicredi.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sicredi.desafio.entity.Pauta;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {

	Pauta findByNome(String nome);
	
	
}
