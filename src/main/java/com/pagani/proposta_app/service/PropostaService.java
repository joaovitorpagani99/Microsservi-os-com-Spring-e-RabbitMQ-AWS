package com.pagani.proposta_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagani.proposta_app.DTO.PropostaRequestDTO;
import com.pagani.proposta_app.DTO.PropostaResponseDTO;
import com.pagani.proposta_app.entity.Proposta;
import com.pagani.proposta_app.mapper.PropostaMapper;
import com.pagani.proposta_app.repository.PropostaRepository;

@Service
public class PropostaService {
	
	@Autowired
	private PropostaRepository repository;

	public PropostaResponseDTO save(PropostaRequestDTO requestDTO) {
		Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(requestDTO);
		repository.save(proposta);
		return PropostaMapper.INSTANCE.convertEntityToDTO(proposta);
	}

	public List<PropostaResponseDTO> findAll() {
		return PropostaMapper.INSTANCE.convertListEntityToListDto(repository.findAll());
	}
}
