package com.sicredi.desafio.pauta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.Optional;

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
import com.sicredi.desafio.mocks.PautaMock;
import com.sicredi.desafio.repository.PautaRepository;
import com.sicredi.desafio.service.PautaServiceImpl;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class) 
public class PautaServiceTest {
	
	@InjectMocks
	@Spy
	PautaServiceImpl pautaService;
	
	@Mock
	PautaRepository pautaRepository;
	
	@BeforeEach
	public void setup(){
	    MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testeDeCriacaoNovaPauta() {
		Pauta pauta = PautaMock.gerarPautaValida();
		Pauta pautaSalva = PautaMock.gerarPautaValida();
		pautaSalva.setId(1L);
		
		Mockito.when(pautaRepository.findByNome(anyString())).thenReturn(null);
		Mockito.when(pautaRepository.save(pauta)).thenReturn(pautaSalva);
		
		Pauta pautaCriada = pautaService.criarNovaPauta(pauta);
		
		assertNotNull(pautaCriada);
		assertEquals(pautaCriada.getNome(), pauta.getNome());
		assertNotNull(pautaCriada.getId());
	}
	
	@Test
	public void testeDeCriacaoNovaPautaNomeInvalido() {
		Pauta pauta = PautaMock.gerarPautaValida();
		pauta.setNome("");
		
		IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			pautaService.criarNovaPauta(pauta);
		  
		});
		
		String expected = "A Pauta informada para cadastro não é valida.";
		String actual = exception.getMessage();
		
		assertTrue(actual.contains(expected));
	}
	
	@Test
	public void testeDeCriacaoNovaPautaNomeJaExistente() {
		Pauta pauta = PautaMock.gerarPautaValida();
		Pauta pautaExistente = PautaMock.gerarPautaValida();
				
		
		Mockito.when(pautaRepository.findByNome(anyString())).thenReturn(pautaExistente);
		
		IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			pautaService.criarNovaPauta(pauta);
		  
		});
		
		String expected = "Já existe Pauta cadastrada com esse nome.";
		String actual = exception.getMessage();
		
		assertTrue(actual.contains(expected));
	}
	
	@Test
	public void testeConsultaPauta() {
		Optional<Pauta> pauta = Optional.of(PautaMock.gerarPautaValida());
					
		Mockito.when(pautaRepository.findById(anyLong())).thenReturn(pauta);
		
		Pauta pautaEncontrada = pautaService.consultarPauta(1L);
		
		
		assertNotNull(pautaEncontrada);
		assertEquals(pautaEncontrada.getNome(), pauta.get().getNome());
		
	}

}
