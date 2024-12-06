package com.pagani.analiseCredito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pagani.analiseCredito.Domain.Proposta;
import com.pagani.analiseCredito.exception.StrategyException;
import com.pagani.analiseCredito.service.strategy.CalculoPonto;

@Service
public class AnaliseCreditoService {

	@Autowired
	private List<CalculoPonto> calculoPontoList;

	@Autowired
	private NotificacaoRabiitService notificacaoRabiitService;

	@Value("${rabbitmq.exchange.proposta.concluida}")
	private String exchangePropostaConcluida;

	public AnaliseCreditoService(List<CalculoPonto> calculoPontoList) {
		this.calculoPontoList = calculoPontoList;
	}

	public void analisar(Proposta proposta) {
		try {
			int pontos = calculoPontoList.stream().mapToInt(impl -> impl.calcular(proposta)).sum();
			proposta.setAprovada(pontos > 350);
		} catch (StrategyException e) {
			proposta.setAprovada(false);
			proposta.setObservacao(e.getMessage());
		}
		notificacaoRabiitService.notificar(exchangePropostaConcluida, proposta);
	}

}
