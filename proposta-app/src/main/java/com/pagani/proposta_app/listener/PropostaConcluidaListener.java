package com.pagani.proposta_app.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pagani.proposta_app.DTO.PropostaResponseDTO;
import com.pagani.proposta_app.entity.Proposta;
import com.pagani.proposta_app.mapper.PropostaMapper;
import com.pagani.proposta_app.repository.PropostaRepository;
import com.pagani.proposta_app.service.WebSocketService;

@Component 
public class PropostaConcluidaListener {

	@Autowired
	private PropostaRepository propostaRepository;
	
	@Autowired
	private WebSocketService socketService;
	
	@RabbitListener(queues = "${rabbitmq.queue.proposta.concluida}")
	public void propostaConcluida(Proposta proposta) {
		propostaRepository.save(proposta);
		PropostaResponseDTO responseDTO = PropostaMapper.INSTANCE.convertEntityToDTO(proposta);
		socketService.notificar(responseDTO);
	}
}
