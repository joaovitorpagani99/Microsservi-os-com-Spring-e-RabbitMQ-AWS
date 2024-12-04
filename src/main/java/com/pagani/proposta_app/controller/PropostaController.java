package com.pagani.proposta_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pagani.proposta_app.DTO.PropostaRequestDTO;
import com.pagani.proposta_app.DTO.PropostaResponseDTO;
import com.pagani.proposta_app.service.PropostaService;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

	@Autowired
	private PropostaService propostaService;
	
	@PostMapping
	public ResponseEntity<PropostaResponseDTO> save(@RequestBody PropostaRequestDTO requestDTO) {
		PropostaResponseDTO response = propostaService.save(requestDTO);
		return ResponseEntity.ok(response);
	}

}
