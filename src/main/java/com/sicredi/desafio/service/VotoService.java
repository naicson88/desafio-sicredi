package com.sicredi.desafio.service;

import com.sicredi.desafio.data.dto.ResultadoVotacaoPautaDto;
import com.sicredi.desafio.entity.Voto;

public interface VotoService {
	
	public Voto votarPauta(Voto voto);

	ResultadoVotacaoPautaDto contabilizarVotos(Long pautaId);

	ResultadoVotacaoPautaDto consultarResultadoPauta(Long pautaId);
}
