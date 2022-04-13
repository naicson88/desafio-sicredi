package com.sicredi.desafio.mocks;

import java.util.Date;

import com.sicredi.desafio.entity.Pauta;
import com.sicredi.desafio.entity.Sessao;

public class SessaoMock {
	
	public static Sessao gerarSessaoValida() {
		Pauta pauta = PautaMock.gerarPautaValida();
		Sessao sessao = new Sessao(new Date(), new Date(), pauta, false);
		
		return sessao;
	}
}
