package com.pagani.analiseCredito.service.strategy.impl;

import java.util.Random;

import com.pagani.analiseCredito.Domain.Proposta;
import com.pagani.analiseCredito.service.strategy.CalculoPonto;

public class OutrosEmprestimosEmAndamento implements CalculoPonto {

	@Override
	public int calcular(Proposta proposta) {
		return outrosEmprestimosEmAndamento() ? 0 : 80;
	}

	private boolean outrosEmprestimosEmAndamento() {
		return new Random().nextBoolean();
	}

}
