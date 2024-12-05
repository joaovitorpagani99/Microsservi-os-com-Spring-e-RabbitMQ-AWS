package com.pagani.proposta_app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proposta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double valorSolicitado;
	
	private int prazoPagamento;
	
	private Boolean aprovada;
	
	private boolean integrada;
	
	private String observacao;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_usuario")
	@JsonManagedReference
	private Usuario usuario;
}
