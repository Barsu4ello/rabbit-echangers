package com.example.rabbitdemo.replyTo;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("producer")
@Configuration
public class QueueConfig {

    public static final String QUEUE_1 = "demo.queue.one";

    @Bean
    public Queue queueOne() {
        return new Queue(QUEUE_1, true);
    }

    @Bean
    public Exchange exchange() {
        return new DirectExchange("directExchange", true, false);
    }


    @Bean
    public Binding bindingQueue1(Queue queueOne, DirectExchange topicExchange) {
        return BindingBuilder.bind(queueOne).to(topicExchange).with("q1");
    }

}
