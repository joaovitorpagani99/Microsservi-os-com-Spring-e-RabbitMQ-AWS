package com.pagani.analiseCredito.service.strategy.impl;

import java.util.Random;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.pagani.analiseCredito.Domain.Proposta;
import com.pagani.analiseCredito.constantes.MensagemConstante;
import com.pagani.analiseCredito.exception.StrategyException;
import com.pagani.analiseCredito.service.strategy.CalculoPonto;

@Order(2)
@Component
public class PontuacaoScoreImpl implements CalculoPonto {

	@Override
	public int calcular(Proposta proposta) {
		int score = score();

		if (score < 200) {
			throw new StrategyException(
					String.format(MensagemConstante.PONTUACAO_SERASA_BAIXA, proposta.getUsuario().getNome()));
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
