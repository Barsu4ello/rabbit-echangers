package com.example.rabbitdemo.deadLetter;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.HashMap;
import java.util.Map;

@Profile("producer")
@Configuration
public class QueueConfig {

    public static final String QUEUE_1 = "deadLetterQueue";
    public static final String QUEUE_2 = "mainQueue";

    @Bean
    public Queue queueOne() {
        return new Queue(QUEUE_1, true);
    }

    @Bean
    public Queue queueTwo() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", "deadLetter");
        args.put("x-dead-letter-routing-key", "dead");

        args.put("x-message-ttl", 10000);

        return new Queue(QUEUE_2, true, false, false, args);
    }

    @Bean
    public Exchange exchange() {
        return new DirectExchange("deadLetter", true, false);
    }


    @Bean
    public Binding bindingDeadLetterQueue(Queue queueOne, DirectExchange directExchange) {
        return BindingBuilder.bind(queueOne).to(directExchange).with("dead");
    }

}
