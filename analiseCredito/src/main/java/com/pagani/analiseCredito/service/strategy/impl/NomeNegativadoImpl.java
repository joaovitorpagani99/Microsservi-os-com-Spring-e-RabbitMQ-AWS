package com.pagani.analiseCredito.service.strategy.impl;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.pagani.analiseCredito.Domain.Proposta;
import com.pagani.analiseCredito.service.strategy.CalculoPonto;

@Component
public class NomeNegativadoImpl  implements CalculoPonto{

	@Override
	public int calcular(Proposta proposta) {
		// TODO Auto-generated method stub
		
		if (nomeNegativado()) {
			throw new RuntimeException("Nome negativado;=.");
		}
		
		return 100;
	}
	
	private boolean nomeNegativado() {
		return new Random().nextBoolean();
	}

}
