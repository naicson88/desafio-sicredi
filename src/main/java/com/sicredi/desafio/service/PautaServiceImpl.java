package com.sicredi.desafio.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sicredi.desafio.entity.Pauta;
import com.sicredi.desafio.repository.PautaRepository;

@Service
public class PautaServiceImpl implements PautaService {
	
	@Autowired
	PautaRepository pautaRepository;


	@Override
	public Pauta criarNovaPauta(Pauta pauta) {
		
		this.validarNovaPauta(pauta);
		
		Pauta pautaCriada = pautaRepository.save(pauta);
		
		if(pautaCriada == null || pautaCriada.getId() == null)
			throw new RuntimeException("Não foi possível cadastrar uma nova pauta.");
		
		return pautaCriada;
	}
	
	private void validarNovaPauta(Pauta pauta) {		
				
			if(pauta == null)
				throw new IllegalArgumentException("A Pauta informada para cadastro não é valida.");
			
			if(pauta.getNome() == null || pauta.getNome().isBlank())
				throw new IllegalArgumentException("A Pauta informada para cadastro não é valida.");
			
			 Pauta pautaComMesmonome = pautaRepository.findByNome(pauta.getNome());
			 
			 if(pautaComMesmonome != null && pautaComMesmonome.getNome().equals(pauta.getNome()))
				 throw new IllegalArgumentException("Já existe Pauta cadastrada com esse nome.");
			
			if(pauta.getNome().length() < 2)
				throw new IllegalArgumentException("O Nome da Pauta precisa ter pelo menos 3 caracteres.");
				
	}

	@Override
	public Pauta consultarPauta(Long pautaId) {
		
		if(pautaId == null || pautaId == 0)
			throw new IllegalArgumentException("A Pauta informada para cadastro não é valida.");
		
		Pauta pauta = pautaRepository.findById(pautaId)
				.orElseThrow(() -> new EntityNotFoundException("Pauta não encontrada com Id informado"));
		
		return pauta;
	}	
	
	
}
