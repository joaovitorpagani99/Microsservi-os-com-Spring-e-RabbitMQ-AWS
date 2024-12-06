package com.pagani.analiseCredito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagani.analiseCredito.Domain.Proposta;
import com.pagani.analiseCredito.service.strategy.CalculoPonto;

@Service
public class AnaliseCreditoService {
	
	@Autowired
	private List<CalculoPonto> calculoPontoList;
	
	public AnaliseCreditoService(List<CalculoPonto> calculoPontoList) {
		this.calculoPontoList = calculoPontoList;
	}
	
	public void analisar(Proposta proposta) {
		int pontuacao = calculoPontoList.stream().mapToInt(impl -> impl.calcular(proposta)).sum();
	}

}
