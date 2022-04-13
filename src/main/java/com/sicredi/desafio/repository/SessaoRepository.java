package com.sicredi.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sicredi.desafio.entity.Sessao;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {
	
	@Query(value = " select case when s.data_fim > sysdate() then 'true' else 'false' end "
			+ " from sessao s where pauta_id = :pautaId "
			+ " order by 1 desc "
			+ " limit 1 "
			, nativeQuery = true)	
	public String isSessaoAbertaParaVotacao(Long pautaId);
	
//	@Query(value = " select * from sessao where id = :id ", nativeQuery = true)
//	public Sessao encontrar(Long id);
	
	@Modifying
	@Query(value = " update sessao set enviado_para_topico = 1 where pauta_id = :id limit 1; ", nativeQuery = true)
	public void atualizarStatusEnvioFilaSessao(Long id);

}
