package com.sicredi.desafio.service;

import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sicredi.desafio.entity.Pauta;
import com.sicredi.desafio.entity.Sessao;
import com.sicredi.desafio.repository.SessaoRepository;

@Service
public class SessaoServiceImpl implements SessaoService {
	
	@Autowired
	PautaService pautaService;
	
	@Autowired
	SessaoRepository sessaoRepository;
	
	Logger logger = LoggerFactory.getLogger(SessaoServiceImpl.class);

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Sessao iniciarSessaoVotacao(Long pautaId, Integer tempoSessaoAtivaEmMinutos) {
		
		Pauta pautaSessao = this.validarNovaSessao(pautaId, tempoSessaoAtivaEmMinutos);
		
		Date dataFim =  converterTempoSessaoAtiva(tempoSessaoAtivaEmMinutos);
				
		Sessao novaSessao = sessaoRepository.save(new Sessao(new Date(), dataFim, pautaSessao, false));
		
		return novaSessao;
		
	}
	
	private Pauta validarNovaSessao(Long pautaId, Integer tempoSessaoAtivaEmMinutos) {
			
		if(tempoSessaoAtivaEmMinutos != null && tempoSessaoAtivaEmMinutos < 1)
			throw new IllegalArgumentException("O tempo de atividade da sessão informado é invalido");
		
		Pauta pauta = pautaService.consultarPauta(pautaId);
		
		return pauta;
		
	}
	
	public Date converterTempoSessaoAtiva(Integer minutosAtividadeSessao) {
		
		  Calendar c = Calendar.getInstance();
		  Date dataAtual = new Date();
		if(minutosAtividadeSessao == null) {
			 
			  c.setTime(dataAtual);
			  c.add(Calendar.MINUTE, 1);
			  return c.getTime();
		}
			
		if(minutosAtividadeSessao < 0) 
			throw new IllegalArgumentException("Tempo estimado da Sessao invalido");
			
			    Duration d = Duration.ofMinutes(minutosAtividadeSessao);
		        int days = (int) d.toDaysPart();
		        int hours = d.toHoursPart();
		        int min = d.toMinutesPart();
		        int sec = d.toSecondsPart();
		       
		        c.setTime(dataAtual);
		        
		        c.add(Calendar.DAY_OF_MONTH, days);
		        c.add(Calendar.HOUR, hours);
		        c.add(Calendar.MINUTE, min);
		        c.add(Calendar.SECOND, sec);
		        
		       return c.getTime();
	}
	
	@Override
	public Boolean isSessaoAbertaParaVotacao(Long pautaId) {
		
		if(pautaId == null || pautaId == 0)
			throw new IllegalArgumentException("O Id da Pauta informada é invalido");
		
		String isValidParaVotacao = sessaoRepository.isSessaoAbertaParaVotacao(pautaId);
		
		if(isValidParaVotacao == null)
			throw new EntityNotFoundException("Não foi possivel encontrar Sessão com Id da Pauta informado.");
		
		Boolean isSessaoAberta = Boolean.valueOf(isValidParaVotacao);
		
		return isSessaoAberta;
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void atualizarStatusEnvioFilaSessao(Long pautaId) {
		if(pautaId == null || pautaId == 0)
			throw new IllegalArgumentException("O Id da Pauta informada é invalido");	
		
		sessaoRepository.atualizarStatusEnvioFilaSessao(pautaId);
		
	}	
	
	
}
