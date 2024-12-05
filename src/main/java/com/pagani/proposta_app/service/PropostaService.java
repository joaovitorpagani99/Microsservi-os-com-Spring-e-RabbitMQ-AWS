package com.pagani.proposta_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	private NotificacaoService notificacaoService;
	private String exchange;

	public PropostaService(PropostaRepository repository, NotificacaoService notificacaoService,
			@Value("${rabbitmq.propostapendente.exchange}") String exchange) {
		this.repository = repository;
		this.notificacaoService = notificacaoService;
		this.exchange = exchange;
	}

	public PropostaResponseDTO save(PropostaRequestDTO requestDTO) {
		Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(requestDTO);
		repository.save(proposta);
		PropostaResponseDTO response = PropostaMapper.INSTANCE.convertEntityToDTO(proposta);
		notificacaoService.notificar(response, "proposta-pendente.ex");
		return response;
	}

	public List<PropostaResponseDTO> findAll() {
		return PropostaMapper.INSTANCE.convertListEntityToListDto(repository.findAll());
	}
}
