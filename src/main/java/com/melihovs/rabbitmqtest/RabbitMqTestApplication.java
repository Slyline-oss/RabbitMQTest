package com.melihovs.rabbitmqtest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@EnableScheduling
@SpringBootApplication
public class RabbitMqTestApplication {

    public static void main(String[] args) throws JsonProcessingException, InterruptedException {
       ConfigurableApplicationContext context = SpringApplication.run(RabbitMqTestApplication.class, args);
       RabbitMqSender sender = context.getBean(RabbitMqSender.class);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();
       //Obejcts
        TestObject object1 = new TestObject("Artjoms", "Melihovs", dtf.format(time));

        Thread.sleep(1000);

        time = LocalDateTime.now();
        TestObject object2 = new TestObject("Brad", "Pitt", dtf.format(time));
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        sender.sendMessage(ow.writeValueAsString(object1));
        sender.sendMessage(ow.writeValueAsString(object2));
    }

}
