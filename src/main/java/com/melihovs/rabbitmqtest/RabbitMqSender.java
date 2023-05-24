package com.melihovs.rabbitmqtest;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqSender {

    private final RabbitTemplate rabbitTemplate;
    private final Queue myQueue;

    @Autowired
    public RabbitMqSender(RabbitTemplate rabbitTemplate, Queue myQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.myQueue = myQueue;
    }

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(myQueue.getName(), message);
    }
}
