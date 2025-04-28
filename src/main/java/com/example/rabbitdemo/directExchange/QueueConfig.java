package com.example.rabbitdemo.directExchange;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Bean
    public Exchange exchange() {
        return new DirectExchange("directExchange", true, false);
    }

    @Bean
    public Binding bindingQueue1(@Qualifier("queueOne") Queue queueOne, DirectExchange directExchange) {
        return BindingBuilder.bind(queueOne).to(directExchange).with("q1");
    }

    @Bean
    public Binding bindingQueue2(Queue queueTwo, DirectExchange directExchange) {
        return BindingBuilder.bind(queueTwo).to(directExchange).with("q2");
    }

//    @Bean
//    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
//        RabbitAdmin admin = new RabbitAdmin(connectionFactory);
//        admin.setAutoStartup(true);
//        return admin;
//    }
}
