package com.pagani.proposta_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.pagani.proposta_app.DTO.PropostaResponseDTO;

@Service
public class WebSocketService {
	
	@Autowired
	private SimpMessagingTemplate template;
	
	public void notificar (PropostaResponseDTO proposta) {
		template.convertAndSend("/propostas", proposta);
	}

}
