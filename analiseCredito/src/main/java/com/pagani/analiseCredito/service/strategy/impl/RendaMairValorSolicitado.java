package com.pagani.analiseCredito.service.strategy.impl;

import org.springframework.stereotype.Component;

import com.pagani.analiseCredito.Domain.Proposta;
import com.pagani.analiseCredito.service.strategy.CalculoPonto;

@Component
public class RendaMairValorSolicitado implements CalculoPonto {

	@Override
	public int calcular(Proposta proposta) {
		// TODO Auto-generated method stub
		return rendaMaiorValorSolicitado(proposta) ? 100 : 0;
	}

	private boolean rendaMaiorValorSolicitado(Proposta proposta) {
		return proposta.getUsuario().getRenda() > proposta.getValorSolicitado();
	}

}
