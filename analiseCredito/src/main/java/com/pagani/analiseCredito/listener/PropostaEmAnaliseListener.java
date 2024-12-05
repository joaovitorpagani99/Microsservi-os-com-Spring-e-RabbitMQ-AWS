package com.pagani.analiseCredito.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import com.pagani.analiseCredito.Domain.Proposta;

@Configuration
public class PropostaEmAnaliseListener {

	@RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
	public void propostaEmAnalise(Proposta proposta) {
		
	}
}
