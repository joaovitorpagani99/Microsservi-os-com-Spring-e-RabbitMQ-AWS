package com.pagani.analiseCredito.service.strategy.impl;

import java.util.Random;

import com.pagani.analiseCredito.Domain.Proposta;
import com.pagani.analiseCredito.service.strategy.CalculoPonto;

public class PontuacaoScoreImpl implements CalculoPonto {

	@Override
	public int calcular(Proposta proposta) {
		int score = score();

		if (score <= 200) {
			throw new RuntimeException("Score abaixo");
		} else if (score <= 400) {
			return 150;
		} else if (score <= 600) {
			return 220;
		}

		return 0;
	}

	private int score() {
		return new Random().nextInt(0, 1000);
	}
}
