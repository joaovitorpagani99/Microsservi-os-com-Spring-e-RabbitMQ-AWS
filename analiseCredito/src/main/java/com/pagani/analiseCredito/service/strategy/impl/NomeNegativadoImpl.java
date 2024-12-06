package com.pagani.analiseCredito.service.strategy.impl;

import java.util.Random;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.pagani.analiseCredito.Domain.Proposta;
import com.pagani.analiseCredito.constantes.MensagemConstante;
import com.pagani.analiseCredito.exception.StrategyException;
import com.pagani.analiseCredito.service.strategy.CalculoPonto;

@Order(1)
@Component
public class NomeNegativadoImpl implements CalculoPonto {

	@Override
	public int calcular(Proposta proposta) {
		// TODO Auto-generated method stub

		if (nomeNegativado()) {
			throw new StrategyException(
					String.format(MensagemConstante.CLIENTE_NEGATIVADO, proposta.getUsuario().getNome()));
		}

		return 100;
	}

	private boolean nomeNegativado() {
		return new Random().nextBoolean();
	}

}
