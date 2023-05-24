package com.melihovs.rabbitmqtest;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class RabbitMqConsumer {

    @RabbitListener(queues = "hello")
    public void consume(String object) {
        System.out.println("amelihar1:" + object);
    }
}
