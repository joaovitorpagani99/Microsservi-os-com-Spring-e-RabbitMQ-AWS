package com.pagani.proposta_app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PropostaRequestDTO {

	private String nome;
	private String sobrenome;
	private String telefone;
	private String cpf;
	private Double renda;
	private Double valorSolicitado;
	private int prazoPagamento;
}
