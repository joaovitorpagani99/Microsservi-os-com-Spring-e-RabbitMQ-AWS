package com.pagani.proposta_app.agendador;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pagani.proposta_app.entity.Proposta;
import com.pagani.proposta_app.repository.PropostaRepository;
import com.pagani.proposta_app.service.NotificacaoRabbitService;

@Component
public class PropostaSemIntegracao {
	
	private final Logger logger = LoggerFactory.getLogger(PropostaSemIntegracao.class);

	private PropostaRepository propostaRepository;

	private NotificacaoRabbitService notificacaoRabbitService;

	private String exchange;

	public PropostaSemIntegracao(PropostaRepository propostaRepository,
			NotificacaoRabbitService notificacaoRabbitService,
			@Value("${rabbitmq.propostapendente.exchange}") String exchange) {
		super();
		this.propostaRepository = propostaRepository;
		this.notificacaoRabbitService = notificacaoRabbitService;
		this.exchange = exchange;
	}

	@Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
	public void buscarPropostaSemIntegracao() {
		propostaRepository.findAllByIntegradaIsFalse().forEach(proposta -> {
			try {
				notificacaoRabbitService.notificar(proposta, exchange);
				autalizarProposta(proposta);
			} catch (RuntimeException e) {
				logger.error(e.getMessage());
			}
		});
	}
	
	private void autalizarProposta(Proposta proposta) {
		proposta.setIntegrada(true);
		propostaRepository.save(proposta);
	}
}
