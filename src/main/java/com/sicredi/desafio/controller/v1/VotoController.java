package com.sicredi.desafio.controller.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sicredi.desafio.data.dto.ResultadoVotacaoPautaDto;
import com.sicredi.desafio.entity.Voto;
import com.sicredi.desafio.service.VotoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping({ "api/v1/voto" })
public class VotoController {
	
	@Autowired
	VotoService votoService;
	
	Logger logger = LoggerFactory.getLogger(PautaController.class);
	
	@PostMapping("/votar")
	@ApiOperation(value="Vota uma Pauta cadastrada com Sessao aberta.")
	public ResponseEntity<String> votarPauta(@RequestBody Voto voto){
		Voto votoComputado = votoService.votarPauta(voto);
		
		return new ResponseEntity<String>("Voto computado com sucesso, seu voto foi: " + votoComputado.getEscolha().toString(), HttpStatus.OK);
	}
		
	@GetMapping("/consultar-resultado-votacao")
	@ApiOperation(value="Traz o resultado da votação após Sessao já encerrada.")
	public ResponseEntity<ResultadoVotacaoPautaDto> consultarResultadoPauta(@RequestParam Long pautaId){
		logger.info("Iniciando consulta de votação de uma Pauta...");
		
		ResultadoVotacaoPautaDto resultado = votoService.consultarResultadoPauta(pautaId);
		
		return new ResponseEntity<ResultadoVotacaoPautaDto>(resultado, HttpStatus.OK);	
	}
}
