package com.melihovs.rabbitmqtest;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    private static final String QUEUE_NAME = "hello";
    private final static String RABBITMQ_HOST = "localhost"; // Update with your RabbitMQ server host

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(RABBITMQ_HOST);
        // Set additional connection properties if needed
        connectionFactory.setUsername("guest"); // Replace with your RabbitMQ username
        connectionFactory.setPassword("guest"); // Replace with your RabbitMQ password
        return connectionFactory;
    }

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setRoutingKey(QUEUE_NAME);
        return rabbitTemplate;
    }
}
