package com.pagani.analiseCredito.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.pagani.analiseCredito.Domain.Proposta;
import com.pagani.analiseCredito.service.AnaliseCreditoService;

@Configuration
public class PropostaEmAnaliseListener {

	@Autowired
	private AnaliseCreditoService analiseCreditoService;

	@RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
	public void propostaEmAnalise(Proposta proposta) {
		analiseCreditoService.analisar(proposta);
	}
}
