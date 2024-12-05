package com.pagani.proposta_app.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

	@Bean
	Queue criarFilaPropostaPendentePaganiAnaliseCredito() {
		return QueueBuilder.durable("proposta-pendente.pagani-credito").build();
	}

	@Bean
	Queue criarFilaPropostaPendentePaganiNotificacao() {
		return QueueBuilder.durable("proposta-pendente.pagani-notificacao").build();
	}

	@Bean
	Queue criarFilaPropostaConcluidaPaganiAnaliseCredito() {
		return QueueBuilder.durable("proposta-concluida.pagani-credito").build();
	}

	@Bean
	Queue criarFilaPropostaConcluidaPaganiNotificacao() {
		return QueueBuilder.durable("proposta-concluida.pagani-notificacao").build();
	}
}
