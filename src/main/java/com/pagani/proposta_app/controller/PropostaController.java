package com.pagani.proposta_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
		return ResponseEntity.created(
				ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId()).toUri())
				.body(response);
	}

	@GetMapping
	public ResponseEntity<List<PropostaResponseDTO>> findAllProposta() {
			return ResponseEntity.ok(propostaService.findAll());
	}
}
