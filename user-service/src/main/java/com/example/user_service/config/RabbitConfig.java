package com.example.user_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
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
    public Queue clientValidationRequestQueue() {
        return new Queue("client.validation.request", true);
    }

    // Queue that user-service binds for receiving account events (user-service)
    @Bean
    public Queue userAccountEventsQueue() {
        return new Queue("user.account.events", true);
    }

    // --- Bindings
    @Bean
    public Binding bindValidationRequest(Queue clientValidationRequestQueue, DirectExchange validationExchange) {
        return BindingBuilder.bind(clientValidationRequestQueue).to(validationExchange).with("client.validate");
    }

    @Bean
    public Binding bindUserAccountEvents(Queue userAccountEventsQueue, TopicExchange accountEventExchange) {
        return BindingBuilder.bind(userAccountEventsQueue).to(accountEventExchange).with("account.#");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                        Jackson2JsonMessageConverter converter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(converter);
        return template;
    }

    // --- Message converter (JSON)
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
