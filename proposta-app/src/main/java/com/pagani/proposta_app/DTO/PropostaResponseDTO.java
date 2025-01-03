package com.pagani.proposta_app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PropostaResponseDTO {
	
	private Long id;
	private String nome;
	private String sobrenome;
	private String telefone;
	private String cpf;
	private Double renda;
	private String valorSolicitadoFmt;
	private int prazoPagamento;
	private String aprovado;
	private String observacao;

}
