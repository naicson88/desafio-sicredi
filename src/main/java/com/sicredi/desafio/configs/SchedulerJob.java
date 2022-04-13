package com.sicredi.desafio.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import com.sicredi.desafio.data.dto.ResultadoVotacaoPautaDto;
import com.sicredi.desafio.service.RabbitMQService;
import com.sicredi.desafio.service.SessaoService;
import com.sicredi.desafio.service.VotoService;


@Configuration
@EnableScheduling
public class SchedulerJob {
	
	@Autowired
	private RabbitMQService rabbitService;
	
	@Autowired
	SessaoService sessaoService;
	
	@Autowired
	VotoService votoService;
	
	Logger logger = LoggerFactory.getLogger(SchedulerJob.class);
	
	  @Scheduled(fixedRate = 60000)
	    public void enviarVotacaoParaTopico() {
		  
	      ResultadoVotacaoPautaDto resultadoVotacao = votoService.enviarResutadoParaFila();
	      
	      if(resultadoVotacao != null && resultadoVotacao.getPautaId() > 0) {
	    	  
	    	  sessaoService.atualizarStatusEnvioFilaSessao(resultadoVotacao.getPautaId());
	    	  
	    	  this.rabbitService.enviarMensagemEmJson("VOTACAO_RESULTADO", resultadoVotacao);	    	  
	    	  logger.info("Resultado de Votação enviado para Fila");
	    	  
	      }	      
	      
	 }
}
