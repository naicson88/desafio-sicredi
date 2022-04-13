package com.sicredi.desafio.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sicredi.desafio.data.dto.NovaSessaoDto;
import com.sicredi.desafio.entity.Sessao;
import com.sicredi.desafio.service.SessaoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping({ "api/v1/sessao" })
public class SessaoController {
	
	@Autowired
	SessaoService sessaoService;
	
	@PostMapping("/criar-sessao-votacao")
	@ApiOperation(value="Inicia uma nova Sessao de uma Pauta cadastrada.")
	public ResponseEntity<Sessao> iniciarSessaoVotacao( @RequestBody NovaSessaoDto novaSessao) {
		
		Sessao sessaoCriada = sessaoService.iniciarSessaoVotacao(novaSessao.getPautaId(), novaSessao.getTempoSessaoAtivaEmMinutos());
		
		return new ResponseEntity<Sessao>(sessaoCriada, HttpStatus.CREATED);
	}
}
