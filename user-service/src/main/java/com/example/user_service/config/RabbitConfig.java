package com.example.user_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.constants.RabbitConstants;

@Configuration
public class RabbitConfig {

    @Bean
    public DirectExchange clientValidationExchange() {
        return new DirectExchange(RabbitConstants.CLIENT_VALIDATION_EXCHANGE);
    }

    @Bean
    public DirectExchange accountStatementExchange() {
        return new DirectExchange(RabbitConstants.ACCOUNT_STATEMENT_EXCHANGE);
    }

    @Bean
    public Queue clientValidationQueue() {
        return new Queue(RabbitConstants.CLIENT_VALIDATION_QUEUE, true);
    }

    @Bean
    public Binding bindClientValidationRequest(Queue clientValidationQueue, DirectExchange clientValidationExchange) {
        return BindingBuilder.bind(clientValidationQueue).to(clientValidationExchange).with(RabbitConstants.CLIENT_VALIDATION_BIND);
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter converter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(converter);
        return template;
    }
}
