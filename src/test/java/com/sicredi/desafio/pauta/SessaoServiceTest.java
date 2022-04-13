package com.sicredi.desafio.pauta;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.sicredi.desafio.entity.Pauta;
import com.sicredi.desafio.entity.Sessao;
import com.sicredi.desafio.mocks.PautaMock;
import com.sicredi.desafio.mocks.SessaoMock;
import com.sicredi.desafio.repository.SessaoRepository;
import com.sicredi.desafio.service.PautaServiceImpl;
import com.sicredi.desafio.service.SessaoServiceImpl;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class) 
public class SessaoServiceTest {
	
	@InjectMocks
	@Spy
	SessaoServiceImpl sessaoService;
	
	@Mock
	PautaServiceImpl pautaService;
	
	@Mock
	SessaoRepository sessaoRepository;
	
	@BeforeEach
	public void setup(){
	    MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testInicioNovaSessao() {
		
		Pauta pauta = PautaMock.gerarPautaValida();
		Sessao sessao = SessaoMock.gerarSessaoValida();
		
		Calendar date = Calendar.getInstance();
		long timeInSecs = date.getTimeInMillis();
		Date dtFim = new Date(timeInSecs + (10 * 60 * 1000));
		
		sessao.setDataFim(dtFim);
		
		Mockito.when(pautaService.consultarPauta(anyLong())).thenReturn(pauta);
		Mockito.when(sessaoRepository.save(any(Sessao.class))).thenReturn(sessao);
		
		Sessao sessaoCriada = sessaoService.iniciarSessaoVotacao(1L, 10);
		
		assertNotNull(sessaoCriada);
		assertTrue(sessao.getDataInicio().before(sessao.getDataFim()));
		
		
	}
	
	@Test
	public void erroAoValidarDessaoTempoInvalido() {
		
		IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			sessaoService.iniciarSessaoVotacao(1L, -1);
		  
		});
		
		String expected = "O tempo de atividade da sessão informado é invalido";
		String actual = exception.getMessage();
		
		assertTrue(actual.contains(expected));
	}
	
	@Test
	public void validDateConverter() {
		
		Calendar date = Calendar.getInstance();
		long timeInSecs = date.getTimeInMillis();
		Date dtFim = new Date(timeInSecs + (10 * 60 * 1000));
		
		Date dateConverted = sessaoService.converterTempoSessaoAtiva(10);
		
		assertThat(dateConverted.after(new Date()));
		assertThat(dateConverted.compareTo(dtFim) >= 0);
	}
	
	@Test
	public void validSessaoAbertaParaVotacao() {
		Long pautaId = 1L;
		Mockito.when(sessaoRepository.isSessaoAbertaParaVotacao(pautaId)).thenReturn("true");
		
		Boolean isSessaoAberta = sessaoService.isSessaoAbertaParaVotacao(pautaId);
		
		assertTrue(isSessaoAberta);
	}
	
	@Test
	public void validSessaoFechadaParaVotacao() {
		Long pautaId = 1L;
		Mockito.when(sessaoRepository.isSessaoAbertaParaVotacao(pautaId)).thenReturn("false");
		
		Boolean isSessaoAberta = sessaoService.isSessaoAbertaParaVotacao(pautaId);
		
		assertTrue(!isSessaoAberta);
	}
}
