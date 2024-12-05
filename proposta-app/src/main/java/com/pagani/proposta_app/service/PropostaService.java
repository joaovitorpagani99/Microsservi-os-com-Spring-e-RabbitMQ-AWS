package com.pagani.proposta_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pagani.proposta_app.DTO.PropostaRequestDTO;
import com.pagani.proposta_app.DTO.PropostaResponseDTO;
import com.pagani.proposta_app.entity.Proposta;
import com.pagani.proposta_app.mapper.PropostaMapper;
import com.pagani.proposta_app.repository.PropostaRepository;

@Service
public class PropostaService {
	private PropostaRepository repository;

	private NotificacaoRabbitService notificacaoService;

	private String exchange;

	public PropostaService(PropostaRepository repository, NotificacaoRabbitService notificacaoService,
			@Value("${rabbitmq.propostapendente.exchange}") String exchange) {
		this.repository = repository;
		this.notificacaoService = notificacaoService;
		this.exchange = exchange;
	}

	public PropostaResponseDTO save(PropostaRequestDTO requestDTO) {
		Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(requestDTO);
		repository.save(proposta);
		notificarRabbitMQ(proposta);
		return PropostaMapper.INSTANCE.convertEntityToDTO(proposta);
	}

	private void notificarRabbitMQ(Proposta proposta) {
		try {
			notificacaoService.notificar(proposta, "proposta-pendente.ex");
		} catch (RuntimeException e) {
			proposta.setIntegrada(false);
			repository.save(proposta);
		}
	}

	public List<PropostaResponseDTO> findAll() {
		return PropostaMapper.INSTANCE.convertListEntityToListDto(repository.findAll());
	}
}
