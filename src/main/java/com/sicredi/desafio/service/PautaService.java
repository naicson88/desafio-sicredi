package com.sicredi.desafio.service;

import com.sicredi.desafio.entity.Pauta;

public interface PautaService {
	
	public Pauta criarNovaPauta(Pauta pauta);
	
	public Pauta consultarPauta(Long pautaId);
	
	//public ResultadoVotacaoPautaDto consultarResultadoPauta(Long pautaId);
	
}
