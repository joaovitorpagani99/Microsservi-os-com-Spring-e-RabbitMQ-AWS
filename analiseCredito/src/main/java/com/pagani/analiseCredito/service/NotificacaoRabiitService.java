package com.pagani.analiseCredito.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagani.analiseCredito.Domain.Proposta;

@Service
public class NotificacaoRabiitService {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void notificar(String exchange, Proposta proposta) {
		rabbitTemplate.convertAndSend(exchange, "", proposta);
	}
}
