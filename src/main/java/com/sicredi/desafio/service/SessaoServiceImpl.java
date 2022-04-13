package com.sicredi.desafio.service;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityNotFoundException;

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

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Sessao iniciarSessaoVotacao(Long pautaId, Integer tempoSessaoAtivaEmMinutos) {
		
		Date dataAtual = new Date();
		Date dataFim =  converterTempoSessaoAtiva(tempoSessaoAtivaEmMinutos);
		
		Pauta pautaSessao = this.validarNovaSessao(pautaId, tempoSessaoAtivaEmMinutos);
		Sessao novaSessao = sessaoRepository.save(new Sessao(dataAtual, dataFim, pautaSessao));
		
		Sessao dois = sessaoRepository.encontrar(novaSessao.getId());
		
		return dois;
		
	}
	
	private Pauta validarNovaSessao(Long pautaId, Integer tempoSessaoAtivaEmMinutos) {
		
		Pauta pauta = pautaService.consultarPauta(pautaId);
			
		if(tempoSessaoAtivaEmMinutos != null && tempoSessaoAtivaEmMinutos < 1)
			throw new IllegalArgumentException("O tempo de atividade da sessão informado é invalido");	
		
		return pauta;
		
	}
	
	private Date converterTempoSessaoAtiva(Integer minutosAtividadeSessao) {
		
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
			throw new EntityNotFoundException("O Id da Pauta informada é invalido");
		
//		Date date = new Date();  
//	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
//	    String dataAtual = formatter.format(date);  
		
		String isValidParaVotacao = sessaoRepository.isSessaoAbertaParaVotacao(pautaId);
		
		if(isValidParaVotacao == null)
			throw new EntityNotFoundException("Não foi possivel encontrar Sessão com Id da Pauta informado.");
		
		Boolean isValid = Boolean.valueOf(isValidParaVotacao);
		
		return isValid;
		
	}
	
	
	
}
