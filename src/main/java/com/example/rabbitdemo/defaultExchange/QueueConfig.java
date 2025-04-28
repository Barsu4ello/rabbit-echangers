package com.example.rabbitdemo.defaultExchange;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("producer")
@Configuration
public class QueueConfig {

    public static final String QUEUE_1 = "demo.queue.one";
    public static final String QUEUE_2 = "demo.queue.two";
    public static final String QUEUE_3 = "demo.queue.three";

    @Bean
    public Queue queueOne() {
        return new Queue(QUEUE_1, true);
    }

    @Bean
    public Queue queueTwo() {
        return new Queue(QUEUE_2, true);
    }

    @Bean
    public Queue queueThree() {
        return new Queue(QUEUE_3, true);
    }

//    @Bean
//    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
//        RabbitAdmin admin = new RabbitAdmin(connectionFactory);
//        admin.setAutoStartup(true);
//        return admin;
//    }
}
