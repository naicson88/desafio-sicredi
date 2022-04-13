package com.sicredi.desafio.controller.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sicredi.desafio.entity.Pauta;
import com.sicredi.desafio.service.PautaService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping({ "api/v1/pauta" })
public class PautaController {
	
	@Autowired
	PautaService pautaService;
	
	Logger logger = LoggerFactory.getLogger(PautaController.class);
	
	@PostMapping("/cadastrar-pauta")
	@ApiOperation(value="Retorna uma nova Pauta cadastrada.")
	public ResponseEntity<Pauta> criarNovaPauta(@RequestBody Pauta pauta){
		logger.info("Iniciando criação de uma nova Pauta..."); 
		
		Pauta pautaCriada = pautaService.criarNovaPauta(pauta);
		
		return new ResponseEntity<>(pautaCriada, HttpStatus.CREATED);
	}
	
}
