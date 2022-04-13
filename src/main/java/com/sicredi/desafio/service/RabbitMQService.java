package com.sicredi.desafio.service;

import java.util.logging.ErrorManager;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicredi.desafio.exceptions.ErrorMessage;

@Service
public class RabbitMQService {
	
	@Autowired
	private RabbitTemplate template;
	
	
	public void enviarMensagemEmJson(String nomeDaFila, Object message) {	
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			String json = mapper.writeValueAsString(message);
			this.template.convertAndSend(nomeDaFila, json);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			
		}catch(AmqpException e) {
			throw new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	
	public void sendMessage(String nomeDaFila, Object message) {	
			this.template.convertAndSend(nomeDaFila, message);

	}
}
