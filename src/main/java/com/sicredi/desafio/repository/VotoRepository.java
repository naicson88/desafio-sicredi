package com.sicredi.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sicredi.desafio.entity.Voto;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {
	
	@Query(value = "select * from voto where id_associado = :idAssociado and pauta_id = :pautaId limit 1" , nativeQuery = true)
	public Voto votoUsuario(String idAssociado, Long pautaId);
	
	@Query(value = " select pauta_id from sessao where enviado_para_topico = 0 limit 1;", nativeQuery = true)
	public Long encontrarVotacaoNaoEnviadasParaFila();

	public List<Voto> findByPautaId(Long pautaId);
}
