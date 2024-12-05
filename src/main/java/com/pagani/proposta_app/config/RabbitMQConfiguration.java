package com.pagani.proposta_app.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Define que esta classe contém configurações do RabbitMQ
@Configuration
public class RabbitMQConfiguration {
	
	@Value("${rabbitmq.propostapendente.exchange}")
	private String exchange;


    // Cria uma fila durável para propostas pendentes relacionadas à análise de crédito
    @Bean
    Queue criarFilaPropostaPendentePaganiAnaliseCredito() {
        return QueueBuilder.durable("proposta-pendente.pagani-credito").build();
    }

    // Cria uma fila durável para propostas pendentes relacionadas à notificação
    @Bean
    Queue criarFilaPropostaPendentePaganiNotificacao() {
        return QueueBuilder.durable("proposta-pendente.pagani-notificacao").build();
    }

    // Cria uma fila durável para propostas concluídas relacionadas à análise de crédito
    @Bean
    Queue criarFilaPropostaConcluidaPaganiAnaliseCredito() {
        return QueueBuilder.durable("proposta-concluida.pagani-credito").build();
    }

    // Cria uma fila durável para propostas concluídas relacionadas à notificação
    @Bean
    Queue criarFilaPropostaConcluidaPaganiNotificacao() {
        return QueueBuilder.durable("proposta-concluida.pagani-notificacao").build();
    }

    // Cria um RabbitAdmin para gerenciar a configuração do RabbitMQ
    @Bean
    public RabbitAdmin criarRabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    // Inicializa o RabbitAdmin quando a aplicação é iniciada
    @Bean
    public ApplicationListener<ApplicationEvent> inicializarAdmin(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }

    // Cria uma FanoutExchange para propostas pendentes
    @Bean // Adicionado @Bean para registrar corretamente no contexto Spring
    public FanoutExchange criarFanoutExchangePropostaPendente() {
        return ExchangeBuilder.fanoutExchange(exchange).build();
    }

    // Cria um binding entre a fila de análise de crédito e o exchange de propostas pendentes
    @Bean
    public Binding criarBindingPropostaPendenteAnaliseCredito() {
        return BindingBuilder.bind(criarFilaPropostaPendentePaganiAnaliseCredito())
                .to(criarFanoutExchangePropostaPendente());
    }

    // Cria um binding entre a fila de notificação e o exchange de propostas pendentes
    @Bean
    public Binding criarBindingPropostaPendenteNotificacao() {
        return BindingBuilder.bind(criarFilaPropostaPendentePaganiNotificacao())
                .to(criarFanoutExchangePropostaPendente());
    }
    
    @Bean
    public MessageConverter jackson2MessageConverter() {
    	return new Jackson2JsonMessageConverter();
    }
    
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    	RabbitTemplate rabbitTemplate = new RabbitTemplate();
    	rabbitTemplate.setConnectionFactory(connectionFactory);
    	rabbitTemplate.setMessageConverter(jackson2MessageConverter());
    	return rabbitTemplate;
    }
    
  
    
    
    
    
    
     
    
    
    
    
    
    
    
    
    
}
