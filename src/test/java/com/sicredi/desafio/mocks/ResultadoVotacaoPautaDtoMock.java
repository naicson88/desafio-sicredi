package com.sicredi.desafio.mocks;

import com.sicredi.desafio.data.dto.ResultadoVotacaoPautaDto;

public class ResultadoVotacaoPautaDtoMock {
	
	public static ResultadoVotacaoPautaDto gerarResultado() {
		
		ResultadoVotacaoPautaDto dto = new ResultadoVotacaoPautaDto();
		dto.setPauta(1L);
		dto.setVotosNao(10L);
		dto.setVotosSim(5L);
		dto.setTotalDeVotos(15);
		
		return dto;
	}
}
