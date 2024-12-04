package com.pagani.proposta_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagani.proposta_app.DTO.PropostaRequestDTO;
import com.pagani.proposta_app.DTO.PropostaResponseDTO;
import com.pagani.proposta_app.entity.Proposta;
import com.pagani.proposta_app.repository.PropostaRepository;

@Service
public class PropostaService {
	
	@Autowired
	private PropostaRepository repository;

	public PropostaResponseDTO save(PropostaRequestDTO requestDTO) {
		repository.save(new Proposta());
		return null;
	}
}
