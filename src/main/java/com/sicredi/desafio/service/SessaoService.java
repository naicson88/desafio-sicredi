package com.sicredi.desafio.service;

import com.sicredi.desafio.entity.Sessao;

public interface SessaoService {
	
	public Sessao iniciarSessaoVotacao(Long pautaId, Integer tempoSessaoAtivaEmMinutos);
	
	public Boolean isSessaoAbertaParaVotacao(Long pautaId);
	
}
