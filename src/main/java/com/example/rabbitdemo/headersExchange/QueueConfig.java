package com.example.rabbitdemo.headersExchange;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.HashMap;
import java.util.Map;

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
        return new HeadersExchange("headersExchange", true, false);
    }


    @Bean
    public Binding bindingQueue1(Queue queueOne, HeadersExchange topicExchange) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("testheader", "123");
        headers.put("testheader2", "1234");
        return BindingBuilder.bind(queueOne).to(topicExchange).whereAny(headers).match();
    }

    @Bean
    public Binding bindingQueue2(Queue queueTwo, HeadersExchange topicExchange) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("testheader", "123");
        headers.put("testheader2", "1234");
        return BindingBuilder.bind(queueTwo).to(topicExchange).whereAll(headers).match();
    }

}
