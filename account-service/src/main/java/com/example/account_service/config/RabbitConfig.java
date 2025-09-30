package com.example.account_service.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    // --- Exchanges
    @Bean
    public DirectExchange validationExchange() {
        return new DirectExchange("client.validation.exchange");
    }

    @Bean
    public TopicExchange accountEventExchange() {
        return new TopicExchange("account.events.exchange");
    }

    // --- Queues
    @Bean
    public Queue clientValidationResponseQueue() {
        return new Queue("client.validation.response", true);
    }

    // --- Message converter (JSON)
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         Jackson2JsonMessageConverter converter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(converter);
        return template;
    }
}