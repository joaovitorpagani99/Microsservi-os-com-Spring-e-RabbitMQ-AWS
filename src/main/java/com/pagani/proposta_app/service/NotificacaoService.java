package com.pagani.proposta_app.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagani.proposta_app.DTO.PropostaResponseDTO;

@Service
public class NotificacaoService {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void notificar(PropostaResponseDTO proposta, String exchange) {
		rabbitTemplate.convertAndSend(exchange,"", proposta);
	}
}
