package com.pagani.proposta_app.service;

import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagani.proposta_app.entity.Proposta;

@Service
public class NotificacaoRabbitService {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void notificar(Proposta proposta, String exchange, MessagePostProcessor messagePostProcessor) {
		rabbitTemplate.convertAndSend(exchange,"", proposta, messagePostProcessor);
	}
	
	public void notificar(Proposta proposta, String exchange) {
		rabbitTemplate.convertAndSend(exchange,"", proposta);
	}
}
