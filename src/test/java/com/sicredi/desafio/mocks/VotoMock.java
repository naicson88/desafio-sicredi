package com.sicredi.desafio.mocks;

import com.sicredi.desafio.entity.Voto;

public class VotoMock {
	
	public static Voto gerarVotoValido() {
		
		Voto voto = new Voto();
		voto.setEscolha("SIM");
		voto.setIdAssociado("00055547400035");
		voto.setPauta(PautaMock.gerarPautaValida());
		
		return voto;
	}
}
