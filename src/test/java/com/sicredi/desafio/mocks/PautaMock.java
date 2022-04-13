package com.sicredi.desafio.mocks;

import com.sicredi.desafio.entity.Pauta;

public class PautaMock {
	
	public static Pauta gerarPautaValida() {
		
		Pauta pauta = new Pauta();
		pauta.setId(1L);
		pauta.setNome("Pauta de Teste");
		
		return pauta;
	}
}
