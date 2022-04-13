package com.sicredi.desafio.service;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sicredi.desafio.data.dto.ResultadoVotacaoPautaDto;
import com.sicredi.desafio.data.enums.EscolhaVoto;
import com.sicredi.desafio.entity.Voto;
import com.sicredi.desafio.exceptions.ErrorMessage;
import com.sicredi.desafio.repository.VotoRepository;
import com.sicredi.desafio.restTemplate.ValidarCPFRestTemplate;

@Service
public class VotoServiceImpl implements VotoService {
	
	@Autowired
	VotoRepository votoRepository;
	@Autowired
	ValidarCPFRestTemplate validarCpfAssociado;
	@Autowired
	SessaoService sessaoService;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Voto votarPauta(Voto voto) {
		
		this.validarVoto(voto);
		
		this.validarSessaoParaPauta(voto.getPauta().getId());
		
		this.validarAssociadoAptoAVotar(voto.getIdAssociado());
			
		voto.setEscolha(voto.getEscolha().toUpperCase());
			
		Voto votoRegistrado = votoRepository.save(voto);
		
		if(votoRegistrado== null) 
			throw new RuntimeException("Não foi possível registrar o voto");
		
		return votoRegistrado;
					
	}
	
	private void validarVoto(Voto voto) {
			
			if(voto == null || voto.getIdAssociado() == null) 
				throw new IllegalArgumentException("Parametros informados para votação invalidos");
				
			if(voto.getEscolha() == null || voto.getEscolha().isBlank() || !EscolhaVoto.validar(voto.getEscolha().toUpperCase()))
				throw new IllegalArgumentException("A escolha informada é invalida, escolha apenas SIM ou NÃO");
					
			if(voto.getPauta().getId() == null || voto.getPauta().getId() == 0)
				throw new IllegalArgumentException("O Id da Pauta informada é invalido para votação");
			
			Voto votoUsuario = votoRepository.votoUsuario(voto.getIdAssociado(), voto.getPauta().getId());
			
			if(votoUsuario != null)
				throw new ErrorMessage(HttpStatus.OK, "Você ja registrou seu voto para esta Pauta anteriormente. Seu voto foi: " + votoUsuario.getEscolha().toString());		
	
	}
	
	private void validarAssociadoAptoAVotar(String idAssociado)  {
		
			this.validarCPFAssociado(idAssociado);
			
			String validarUsuarioAptoAVotar = validarCpfAssociado.validarCPFAssociadoAptoAVotar(idAssociado);		
			
			if(validarUsuarioAptoAVotar == null)
				throw new NoSuchElementException("Não foi possível determinar se o usuario está apto a votar");
			
			if("ABLE_TO_VOTE".equalsIgnoreCase(validarUsuarioAptoAVotar))
				return;
			
			else if("UNABLE_TO_VOTE".equalsIgnoreCase(validarUsuarioAptoAVotar))
				throw  new ErrorMessage(HttpStatus.OK, "Usuário não está Apto para votação. UNABLE_TO_VOTE");
			
			else
				throw new RuntimeException("Não foi possível determinar se o Associado está apto para votação, retorno: " + validarUsuarioAptoAVotar);
		
	}
	
	private void validarCPFAssociado(String idAssociado) {
		if(idAssociado.contains(".") || idAssociado.contains("-"))
			throw new IllegalArgumentException("Por favor, insira apenas os numeros do CPF");
	}
	
	private void validarSessaoParaPauta(Long pautaId) {
		
		if(pautaId == null || pautaId == 0)  
			throw new IllegalArgumentException("O Id da Pauta é invalido.");
		
		Boolean isSessaoValidaParaPauta = sessaoService.isSessaoAbertaParaVotacao(pautaId);
		
		if(!isSessaoValidaParaPauta)
			throw new NoSuchElementException("Não existe Sessão ativa para votação desta Pauta");
		
	}
	
	@Override
	public ResultadoVotacaoPautaDto consultarResultadoPauta(Long pautaId) {
		
		if(pautaId == null || pautaId == 0)
			throw new IllegalArgumentException("A Pauta informada para cadastro não é valida.");
		
		ResultadoVotacaoPautaDto resultadoVotacao = this.contabilizarVotos(pautaId);
		resultadoVotacao.setPauta(pautaId);
						
		return resultadoVotacao;

	}
	
	@Override
	public ResultadoVotacaoPautaDto contabilizarVotos(Long pautaId) {
		
		ResultadoVotacaoPautaDto resultadoVotacao = new ResultadoVotacaoPautaDto();
						
			Boolean isSessaoAbertaParaVotacao = sessaoService.isSessaoAbertaParaVotacao(pautaId);
			
			if(isSessaoAbertaParaVotacao)
				throw new ErrorMessage(HttpStatus.OK, "A Sessão desta Pauta continua aberta para votação, aguarde o encerramento para consultar novamente.");
			
			List<Voto> listaDeVotos = votoRepository.findByPautaId(pautaId);
			
			if(listaDeVotos == null || listaDeVotos.isEmpty())
				throw new NoSuchElementException("Não houve nenhuma votação para esta Pauta");
					
			resultadoVotacao.setTotalDeVotos(listaDeVotos.size());
			resultadoVotacao.setVotosSim(listaDeVotos.stream().filter(voto -> voto.getEscolha().equals("SIM")).count());
			resultadoVotacao.setVotosNao(listaDeVotos.stream().filter(voto -> voto.getEscolha().equals("NÃO")).count());

				 
		 return resultadoVotacao;
		
	}
	
	
}
