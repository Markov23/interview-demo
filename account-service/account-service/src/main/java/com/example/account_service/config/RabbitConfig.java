package com.example.account_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${rabbitmq.exchange}")
    private String exchangeName;

    @Value("${rabbitmq.account.queue}")
    private String accountQueueName;

    @Value("${rabbitmq.account.routingkey}")
    private String accountRoutingKey;

    @Bean
    public DirectExchange appExchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Queue accountQueue() {
        return new Queue(accountQueueName, true);
    }

    @Bean
    public Binding bindingAccountQueue(Queue accountQueue, DirectExchange appExchange) {
        return BindingBuilder.bind(accountQueue).to(appExchange).with(accountRoutingKey);
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rt = new RabbitTemplate(connectionFactory);
        rt.setMessageConverter(producerJackson2MessageConverter());
        return rt;
    }
}