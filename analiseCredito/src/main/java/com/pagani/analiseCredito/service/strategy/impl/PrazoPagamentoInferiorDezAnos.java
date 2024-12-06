package com.pagani.analiseCredito.service.strategy.impl;

import com.pagani.analiseCredito.Domain.Proposta;
import com.pagani.analiseCredito.service.strategy.CalculoPonto;

public class PrazoPagamentoInferiorDezAnos implements CalculoPonto {

	@Override
	public int calcular(Proposta proposta) {
		// TODO Auto-generated method stub
		return proposta.getPrazoPagamento() < 120 ? 80 : 0;
	}

}
