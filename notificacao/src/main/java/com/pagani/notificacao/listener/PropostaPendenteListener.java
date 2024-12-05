package com.pagani.notificacao.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pagani.notificacao.constante.MensagemConstante;
import com.pagani.notificacao.domain.Proposta;
import com.pagani.notificacao.service.NotificacaoSnsService;

@Component
public class PropostaPendenteListener {
	
	@Autowired
	private NotificacaoSnsService notificacaoSnsService;
	
	@RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
	public void propostaPendente(Proposta proposta) {
		String mensagem = String.format(MensagemConstante.PROPOSTA_EM_ANALISE, proposta.getUsuario().getNome());
		notificacaoSnsService.notificar(proposta.getUsuario().getTelefone() ,mensagem);
	}

}
