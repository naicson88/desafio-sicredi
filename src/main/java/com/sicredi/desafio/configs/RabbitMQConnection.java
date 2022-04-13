package com.sicredi.desafio.configs;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConnection {
	
	private static final String NOME_EXCHANGE = "amq.direct";
	private AmqpAdmin amqAdmin;
	
	public RabbitMQConnection(AmqpAdmin admin) {
		this.amqAdmin = admin;
	}
	
	private Queue queue(String queueName) {
		return new Queue(queueName, true, false, false);
	}
	
	private DirectExchange directExchange() {
		return new DirectExchange(NOME_EXCHANGE);
	}
	
	private Binding relationship(Queue queue, DirectExchange directExchange) {
		return new Binding(queue.getName(), Binding.DestinationType.QUEUE, directExchange.getName(), queue.getName(), null);
	}
	
	@PostConstruct
	public void add() {
		this.criarFilaVotacao();
		
	}
	
	private void criarFilaVotacao() {
		Queue filaVotacao = this.queue("VOTACAO_RESULTADO");
		
		DirectExchange change = this.directExchange();
		
		Binding votacaoBinding = this.relationship(filaVotacao, change);
		
		//Criando Queues no RabbitMQ
		this.amqAdmin.declareQueue(filaVotacao);
		
		this.amqAdmin.declareExchange(change);
		
		this.amqAdmin.declareBinding(votacaoBinding);
	}
	
	
}
