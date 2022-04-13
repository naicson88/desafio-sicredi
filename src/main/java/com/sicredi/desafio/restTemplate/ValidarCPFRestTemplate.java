package com.sicredi.desafio.restTemplate;

import java.util.NoSuchElementException;
import java.util.logging.ErrorManager;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicredi.desafio.exceptions.ErrorMessage;

@Component
public class ValidarCPFRestTemplate {
	
	private final String schem = "https";
	private final String host = "user-info.herokuapp.com";
		
	public String validarCPFAssociadoAptoAVotar(String idAssociado) {
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		
		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme(this.schem)
				.host(this.host)	
				.path("users/"+idAssociado)
				.build();
		
		String situacaoAssociado = null;
		
		try {
			
			ResponseEntity<String> response = restTemplate.exchange(uri.toUriString(), HttpMethod.GET, 
					 new HttpEntity<Object>(header), String.class);
			
			ObjectMapper mapper = new ObjectMapper();
			JsonNode tree;
		
				tree = mapper.readTree(response.getBody());
		
			JsonNode node = tree.path("status");
			
			if(node.isMissingNode())
				throw new NoSuchElementException("Não foi possível determinar se o usuario está apto a votar");
			
			situacaoAssociado = node.textValue();
			
		}catch (Exception e) {
			throw new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage() + ". Verifique o Id do Associado");
		}	
	  
	  return situacaoAssociado;
	}	
}
