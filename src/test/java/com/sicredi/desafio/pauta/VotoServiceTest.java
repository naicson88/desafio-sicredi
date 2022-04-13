package com.sicredi.desafio.pauta;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

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

import com.sicredi.desafio.data.dto.ResultadoVotacaoPautaDto;
import com.sicredi.desafio.entity.Voto;
import com.sicredi.desafio.exceptions.ErrorMessage;
import com.sicredi.desafio.mocks.VotoMock;
import com.sicredi.desafio.repository.VotoRepository;
import com.sicredi.desafio.restTemplate.ValidarCPFRestTemplate;
import com.sicredi.desafio.service.SessaoServiceImpl;
import com.sicredi.desafio.service.VotoServiceImpl;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class) 
public class VotoServiceTest {
	
	@InjectMocks
	@Spy
	VotoServiceImpl votoService;
	@Mock
	ValidarCPFRestTemplate validarCpfAssociado;
	@Mock
	VotoRepository votoRepository;
	@Mock
	SessaoServiceImpl sessaoService;
	
	@BeforeEach
	public void setup(){
	    MockitoAnnotations.openMocks(this);
	}
		
	@Test
	public void testVotoDeUmaPauta() {
		Voto voto = VotoMock.gerarVotoValido();
		
		Mockito.when(votoRepository.votoUsuario(voto.getIdAssociado(), voto.getPauta().getId())).thenReturn(null);
		Mockito.when(validarCpfAssociado.validarCPFAssociadoAptoAVotar(voto.getIdAssociado())).thenReturn("ABLE_TO_VOTE");
		Mockito.when(sessaoService.isSessaoAbertaParaVotacao(anyLong())).thenReturn(true);
		Mockito.when(votoRepository.save(any(Voto.class))).thenReturn(voto);
		
		Voto votoRegistrado = votoService.votarPauta(voto);
		
		assertNotNull(votoRegistrado);
		assertEquals(votoRegistrado.getEscolha(), voto.getEscolha());
		
		
	}
	
	@Test
	public void testVotoDeUmaPautauUsuarioUnableToVote() {
		Voto voto = VotoMock.gerarVotoValido();
		
		Mockito.when(votoRepository.votoUsuario(voto.getIdAssociado(), voto.getPauta().getId())).thenReturn(null);
		Mockito.when(validarCpfAssociado.validarCPFAssociadoAptoAVotar(voto.getIdAssociado())).thenReturn("UNABLE_TO_VOTE");
		Mockito.when(sessaoService.isSessaoAbertaParaVotacao(anyLong())).thenReturn(true);
	
		ErrorMessage exception = Assertions.assertThrows(ErrorMessage.class, () -> {
			votoService.votarPauta(voto);
		  
		});
		
		String expected = "Usuário não está Apto para votação. UNABLE_TO_VOTE";
		String actual = exception.getMsg();
		
		assertTrue(actual.contains(expected));
	}
	
	@Test
	public void testInvalidEscolhaVoto() {
		Voto voto = VotoMock.gerarVotoValido();
		voto.setEscolha("Escolha Invalida");
		
		IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			votoService.votarPauta(voto);
		  
		});
		
		String expected = "A escolha informada é invalida, escolha apenas SIM ou NÃO";
		String actual = exception.getMessage();
		
		assertTrue(actual.contains(expected));
	}
	
	@Test
	public void enviarResultadoVotacaoParaFila() {
		Long pautaId = 1L;		
		
		Mockito.when(votoRepository.encontrarVotacaoNaoEnviadasParaFila(anyString())).thenReturn(pautaId);
				
		ResultadoVotacaoPautaDto resultado = votoService.enviarResutadoParaFila();
		
		assertNotNull(resultado);
		assertThat(resultado.getVotosNao() > 0);
			
	}
}
